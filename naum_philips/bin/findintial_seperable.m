function beta = findintial_seperable( M, d)
N_d = ceil(M/d);

beta( 1 , : ) = zeros( 1, d + 1 );
beta( 1, end) = 1;

for ii = 1 : d
    beta_added = zeros( N_d, d +  1 );
    beta_added( :, ii ) = 1;
    temp = 0 : 1/N_d : (N_d - 1)/N_d; 
%     temp = [0, rand(1, N_d -1)];    
    beta_added( :, end ) = -temp';
    beta = [beta; beta_added];
end