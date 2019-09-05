package trainingJUnit;

public interface ExprNode {
public static final TypeExpr VAR = TypeExpr.var;
public static final TypeExpr VAL = TypeExpr.val;
public static final TypeExpr ADD = TypeExpr.add;
public static final TypeExpr MUL = TypeExpr.mul;
public static final TypeExpr EXP = TypeExpr.exp;
public static final TypeExpr FUN = TypeExpr.fun;

public TypeExpr getType();
public double getValue();

}
