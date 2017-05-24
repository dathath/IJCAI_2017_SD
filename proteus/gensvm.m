function  [x,ypredicted] = gensvm(X, y,mode)
%mode 1 - semi hard, no false positives
%mode 2 - semi soft, no false negatives
%mode 3 - soft margin
%mode 4 - hard margin
[num_sample,d]=size(X);
mode_control=ones(num_sample,1);
for i = 1:num_sample
    if(mode==1)
        if(y(i)==-1)
            mode_control(i) = 0;
        end
    end
    if(mode==2)
        if(y(i)==1)
            mode_control(i)=0;
        end
    end
    if(mode==4)
    mode_control(i)=0;
    end
end
%[G,beta]= PWL_feature(X, M, beta_type);
%H=[zeros(1,num_sample+1+M); zeros(M,1) eye(M) zeros(M,num_sample); zeros(num_sample,num_sample+1+M)];
%f=[zeros(M+1,1); ones(num_sample,1)];
[~ , M ] = size(X);
A=[];
for i=1:num_sample
s=zeros(1,num_sample);
s(i)=1;
temp_a=-[y(i)*[1 X(i,:)] s];
A=[A;temp_a];
end
A=[A;zeros(num_sample,M+1) -eye(num_sample)];
b=[-ones(num_sample,1); zeros(num_sample,1)];
cvx_begin
    variable x(M+1+num_sample)
    minimize(( 0.5*(x(1:M+1)'*x(1:M+1)) ) + 100*(ones(1,num_sample)* x(M+2:M+1+num_sample)));
    subject to
        A*x<=b;
        (1-mode_control).*x(M+2:M+1+num_sample)==0;
cvx_end
[~ , d ] = size(X);
ypredicted = x(1)+(X*x(2:M+1));
y;
A*x;
A;