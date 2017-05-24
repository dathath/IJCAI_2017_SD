
function vector = unpackValuation( V )
	import proteus.dl.*;

	X = V.getVariables();

	varIterator = X.iterator();

	k = 1;
	vector = [];
	while ( varIterator.hasNext() )
		thisVar = varIterator.next();
		thisReal = V.get( thisVar );

		thisDouble = thisReal.toDouble();
		vector(k) = thisDouble.doubleValue();
		k = k + 1;
	end

	vector = transpose( vector );

end

