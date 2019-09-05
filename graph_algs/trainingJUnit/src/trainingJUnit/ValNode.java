package trainingJUnit;

public class ValNode implements ExprNode {
	private double value;
	public ValNode(double value) {
		this.value = value;
	}
	public ValNode(String valueRead) {
		this.value = Double.valueOf(valueRead);
	}
	public TypeExpr getType() {
		return VAL;
	}
	public double getValue() {
		return this.value;
	}
}
