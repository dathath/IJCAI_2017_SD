
function valuation = packValuation( names, x )
	import proteus.dl.*;

	valuation = Valuation();

	for i = 1:length( x )
		valuation.put( RealVariable( char(names(i)) ), Real( num2str(x(i)) ) );
	end

end

