function [G,beta] = PWL_feature(X, M, beta_type)

% M: the number of hinge hyperplanes, default m = 10d;
% beta_type: 0: seperable
%            1: non-seperable

[~ , d ] = size(X);

if nargin == 1
    M = 10 * d
    beta_type = 0;
end


if nargin == 2
    beta_type = 0;
end

if beta_type == 1
    beta = findintial( M, d );
else
    beta = findintial_seperable(M,d);
end

G = HH( X , beta, d );
