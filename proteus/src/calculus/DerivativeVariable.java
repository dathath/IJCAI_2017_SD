/**
 * @author Nikos Arechiga
 * Toyota InfoTechnology Center, USA
 * 465 N Bernardo Ave, Mountain View, CA 94043
 */

package calculus;

import dl.syntax.*;

public class DerivativeVariable extends RealVariable {
	protected int order;

	public DerivativeVariable ( int degree, Term y, Term x ) {
		operator = new Operator("derivative" + degree );
		spawnArguments();
		addArgument( y );
		addArgument( x );
	}
	
	public int getOrder() {
		return order;
	}
	
	public Term getNumerator() {
		return (Term)(getArgument(0));
	}
	
	public Term getDenominator() {
		return (Term)(getArgument(1));
	}
	
	public Term getLHS() {
		return getNumerator();
	}
	
	public Term getRHS() {
		return getDenominator();
	}
	

}
