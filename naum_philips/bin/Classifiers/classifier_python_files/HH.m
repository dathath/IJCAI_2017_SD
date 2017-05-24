function [ G , df , beta , M] = HH( x ,beta, type )
[  sample_num , d ] = size( x );
[ M , d_1 ] = size( beta);
G = [];
df = zeros( M , d_1 , sample_num );



for ii = 1 : sample_num
    xx = [];
    for dd = 1 : d ;
        xx = [ xx ; x( ii , dd )];
    end
    xx = [ xx ; 1 ];

    G_add = [];

    G_add = [ G_add , beta( 1 , :) * xx ];


    for dd = 1 : d + 1
        df( 1, dd, ii ) = xx( dd );
    end


    for jj = 2 : M
        rho = beta( jj , :) * xx;
        if rho > 0
            for dd = 1 : d + 1
                df( jj, dd, ii ) = xx( dd );
            end
            G_add = [ G_add , rho ];
        else
            G_add = [G_add , 0];
        end

    end

    G = [ G ; G_add ];
end

try_num = 0;
if nargin == 2
    sensitive_value = 0.001;
    real_M = rank( G , sensitive_value);
    riffruff
    while real_M ~= size(G,2)
        try_num = try_num + 1;
        if try_num > 100
            break;
        end
        for ii = size( G,2) : -1 : 1
	    print("Some shit happening here");
            G_new = G( : ,[ 1 : ( ii -1) , (ii + 1) : size( G,2 )] );
            if rank( G_new , sensitive_value ) >= rank( G , sensitive_value)
                df = df( [ 1 : ( ii -1) , (ii + 1) : size( G,2 )] ,:,:);
                beta = beta( [ 1 : ( ii -1) , (ii + 1) : size( G,2 )] , : );
                G = G_new;
                M = size( G , 2 );
                break;
            end
        end
    end
end


% s_df = reshape( df, size( beta , 1 ) * 3, sample_num );
% ddf = zeros( M * d_1, M*d_1, sample_num );
%
% for ii = 1 : sample_num
%     ddf( : , : , ii ) = ( s_df( : , ii ) * s_df( : , ii )')';
% end
