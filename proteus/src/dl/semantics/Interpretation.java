/**
 * @author Nikos Arechiga
 * @author Anuradha Vakil
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */
package dl.semantics;

import java.util.*;

import dl.syntax.*;

public interface Interpretation {

	public Term evaluateTerm( Term thisTerm, Valuation valuation );
	public Boolean evaluateFormula( dLFormula thisFormula, Valuation valuation );

}
