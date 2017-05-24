function beta = findintial( M, d)

if d == 1
    for ii = 1 : M
        if rand > 0.5
            beta( ii , 1 ) = 1;
        else
            beta( ii , 1 ) = -1;
        end
        beta( ii , 2 ) = - beta( ii , 1 ) * rand;
    end
    beta( 1 , : ) = [0 1];
end


if d == 2
    for ii = 1 : M
        if rand > 0.5
            beta( ii , 1 ) =1;
        else
            beta( ii , 1 ) = -1;
        end
        beta( ii , 2 ) = 2^( floor( 10 * ( rand - 0.5)));
        checking = 1;
        while checking == 1
            beta( ii , 3 ) = ( rand - 0.5 ) * beta( ii , 2 )*10;
            if( beta( ii , :) * [ 0 0 1]' > 0 )
                if( beta( ii , :) * [ 0 1 1]' < 0 )
                    checking = 0;
                end
                if( beta( ii , :) * [ 1 1 1]' < 0 )
                    checking = 0;
                end
                if( beta( ii , :) * [ 1 0 1]' < 0 )
                    checking = 0;
                end
            else
                if( beta( ii , :) * [ 0 1 1]' > 0 )
                    checking = 0;
                end
                if( beta( ii , :) * [ 1 1 1]' > 0 )
                    checking = 0;
                end
                if( beta( ii , :) * [ 1 0 1]' > 0 )
                    checking = 0;
                end
            end
        end
    end
    beta( 1 , : ) = [0 0 1];
end
if d > 2
    beta = [eye(d), zeros(d,1)];
    for ii = 1 : M
        temp = rand( d, d );
        A = [temp( :, 2 : end ), ones( d, 1) ];
        b = temp(:,1);
        beta_add = [1; A^-1*b];
        beta_add
        beta = [beta; beta_add'];
    end
end
