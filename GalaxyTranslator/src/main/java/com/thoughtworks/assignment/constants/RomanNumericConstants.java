package com.thoughtworks.assignment.constants;

public final class RomanNumericConstants {

	public static final String SUBTRACT_X_WITH_L_C = "X can be subtracted from L and C only.";

	public static final String SUBTRACT_C_WITH_D_M = "C can be subtracted from D and M only.";

	public static final String SUBTRACT_I_WITH_V_X = "I can be subtracted from V and X only";

	public static final String SUBTRACT_NOT_POSSIBLE_V_L_D = "V, L, and D can never be subtracted.";

	public static final String UNDECLARED_ALIEN_NUMERAL = "Undeclared Alien Numeral was used, input ignored";

	public static final String NO_IDEA = "I have no idea what you are talking about.";
	
	public static final String HOW_MANY_REG_EXPR = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
	
	public static final String HOW_MUCH_REG_EXPR = "^how much is ((?:\\w+[^0-9] )+)\\?$";
	
	public static final String CREDIT_MATCHER_REG_EXPR = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
	
	public static final String[] ROMAN_REPITITION_REG_EXPR = { "(IIII+)", "(XXXX+)", "(CCCC+)", "(MMMM+)", "(DD+)", "(LL+)", "(VV+)" };
	
	public static final String WORD_REG_EXPR = "^[a-z]+";

}
