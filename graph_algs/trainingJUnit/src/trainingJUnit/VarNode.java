package trainingJUnit;

public class VarNode implements ExprNode {
private String name;
private double value;
private boolean isValSet;

	public VarNode(String name) {
		this.name=name;
		isValSet=false;
	}
	public TypeExpr getType() {
		return VAR;
	}
	public void setValue(double value) {
		this.value = value;
		this.isValSet = true;
	}
	public double getValue(){
		if(isValSet) {
			return this.value;
		} else {
			throw new ArithmeticException("var "+ this.name + " was not initialized.");
		}
	}
}
