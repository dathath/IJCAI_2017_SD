num=2000;
x=zeros(num,2);
y=zeros(num,1);
for i= 1:num
    x1=1.3*rand;
    x2=1.3*rand;
    x(i,:)=[x1 x2];
    if((x1^2 + x2^2) <= 1)
       y(i,1)=+1;
    else
       y(i,1)=-1;
    end
end
num_sample_store=[num];

% ypredicted=quadprogramclassifier(x,y,3,1,1);
% ypred=sign(ypredicted);
% error=y-ypred;
% error_percentage=sum(abs(error/2))/num

figure(2)
hold on;
for i= 1:num
if(y(i)==1)
    plot(x(i,1),x(i,2),'b--o');
else
    plot(x(i,1),x(i,2),'r*');
end
end
error_percentage=100;
error_old=500;
error_store=[];
n=5;
while(error_percentage>=0.05)
ypredicted=quadprogramclassifier(x,y,3,1,1);
%[w,ypredicted]=gensvm(x,y,1);
ypred=sign(ypredicted);
error=y-ypred;
error_percentage=sum(abs(error/2))/num
new_x_data=[];
new_y_data=[];
new_y_pred=[];
[num_samples,~]=size(y);
counter=0;
for i = 1:num_samples
    if((error(i,1) == 2) || (error(i,1)==-2) || y(i)==-1)
          counter=counter+1;
          new_x_data=[new_x_data; x(i,:)];
          new_y_data=[new_y_data; y(i)];
          new_y_pred=[new_y_pred; ypred(i)];

    end

end
[num_samples_temp,~]=size(new_y_data);
num_sample_store= [num_sample_store; num_samples_temp];
if(num_sample_store(end)<num_sample_store(end-1))
        x=new_x_data;
        y=new_y_data;
        ypred=new_y_pred;
        error_old=error_percentage;
        error_store=[error_store; error_percentage];
        figure(n)
        hold on;
        [num_samples,~]=size(y);
        [num,~]=size(y);
        for i= 1:num
        if(y(i)==1)
            plot(x(i,1),x(i,2),'b--o');
        elseif(y(i)==-1)

            plot(x(i,1),x(i,2),'r*');
        end
        end
        n=n+1;
        figure(n)
        hold on;
        for i= 1:num_samples
        if(ypred(i)==1)
            plot(x(i,1),x(i,2),'g--o');
        elseif(ypred(i)==-1)
           plot(x(i,1),x(i,2),'b*');
        end
        end

end

n=n+1;
end
% error_percentage=100;
% figure(3);
% hold on;
% [num,~]=size(y);
% for i= 1:num
% if(y(i)==1)
%     plot(x(i,1),x(i,2),'b--o');
% else
%     plot(x(i,1),x(i,2),'r*');
% end
% end
%
% n=5;
% while(error_percentage>=0.05)
%
% [w,ypredicted]=gensvm(x,y,1);
% ypred=sign(ypredicted);
% error=y-ypred;
% error_percentage=sum(abs(error/2))/num
% new_x_data=[];
% new_y_data=[];
% [num_samples,~]=size(y);
% counter=0;
% for i = 1:num_samples
%     if((error(i,1) == 2) || (error(i,1)==-2) || y(i)==-1)
%           counter=counter+1;
%           new_x_data=[new_x_data; x(i,:)];
%           new_y_data=[new_y_data; y(i)];
%     end
%
% end
% x=new_x_data;
% y=new_y_data;
% figure(n)
% hold on;
% [num_samples,~]=size(y);
% [num,~]=size(y);
% for i= 1:num
% if(y(i)==1)
%     plot(x(i,1),x(i,2),'b--o');
% else
%     plot(x(i,1),x(i,2),'r*');
% end
% end
% n=n+1;
% figure(n)
% hold on;
% for i= 1:num_samples
% if(ypred(i)==1)
%     plot(x(i,1),x(i,2),'g--o');
% else
%    plot(x(i,1),x(i,2),'b*');
% end
% end
%
% n=n+1;
% if(n==9)
%     dumdum
% end
% end
