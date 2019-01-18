package totalcross.sample.components.layout;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.layout.HBox;
import totalcross.ui.layout.VBox;
import totalcross.util.UnitsConverter;

public class CalculatorHBoxVBoxSample extends Container {
	private static final int SPECIAL_OPERATION_ERASE = 1;
    private static final int SPECIAL_OPERATION_CLEAR = 2;
    private static final int SPECIAL_OPERATION_CHANGE_SIGN = 3;
    private static final int SPECIAL_OPERATION_PERCENTAGE = 4;
    private static final int SPECIAL_OPERATION_DOT = 5;
    private static final int OPERATION_EQUALS = 1;
    private static final int OPERATION_PLUS = 2;
    private static final int OPERATION_MINUS = 3;
    private static final int OPERATION_TIMES = 4;
    private static final int OPERATION_DIVISION = 5;
    private AnswerLabel answer;
    private String value = "0";
    private double acumulator = 0;
    private int calcOperation = OPERATION_EQUALS;

    @Override
    public void initUI() {
        this.setBackColor(BRIGHTER_BACKGROUND);

        VBox vboxAll = new VBox(VBox.LAYOUT_FILL, VBox.ALIGNMENT_STRETCH);
        vboxAll.setSpacing(2);
        
        HBox[] rows = new HBox[5];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new HBox(HBox.LAYOUT_FILL, HBox.ALIGNMENT_STRETCH);
            rows[i].setSpacing(2);
            vboxAll.add(rows[i]);
        }

        rows[0].add(new SpecialButton("C", SPECIAL_OPERATION_CLEAR));
        rows[0].add(new SpecialButton("+/-", SPECIAL_OPERATION_CHANGE_SIGN));
        rows[0].add(new SpecialButton("%", SPECIAL_OPERATION_PERCENTAGE));

        for (int i = 0; i <= 8; i++) {
            int row = i / 3;
            rows[3 - row].add(new NumberInputButton(i + 1));
        }

        rows[4].add(new NumberInputButton(0));
        rows[4].add(new SpecialButton(".", SPECIAL_OPERATION_DOT));

        rows[0].add(new SpecialButton("DEL", SPECIAL_OPERATION_ERASE));
        rows[1].add(new OperatorInputButton(OPERATION_DIVISION, "/"));
        rows[2].add(new OperatorInputButton(OPERATION_TIMES, "*"));
        rows[3].add(new OperatorInputButton(OPERATION_MINUS, "-"));
        rows[4].add(new OperatorInputButton(OPERATION_PLUS, "+"));
        rows[4].add(new OperatorInputButton(OPERATION_EQUALS, "="));

        answer = new AnswerLabel();
    	this.add(answer, LEFT, TOP, PARENTSIZE, 120 + DP);
        this.add(vboxAll, CENTER, AFTER, this.width, this.height - answer.getHeight());
    }

    private void clearScreen(boolean clearAll) {
        value = "0";
        if (clearAll) {
        	acumulator = 0;
        }
        answer.resetFontSize();
        answer.setText(value);
    }

    private void operatorInput(int operator) {
    	double parse = Double.parseDouble(value);
        switch (calcOperation) {
            case OPERATION_EQUALS:
                acumulator = parse;
                break;
            case OPERATION_DIVISION:
                acumulator = acumulator / parse;
                break;
            case OPERATION_TIMES:
                acumulator = acumulator * parse;
                break;
            case OPERATION_MINUS:
                acumulator = acumulator - parse;
                break;
            case OPERATION_PLUS:
                acumulator = acumulator + parse;
                break;
            default:
                throw new RuntimeException("'" + operator + "' is not a valid operator");
        }

        clearScreen(false);

        if (operator == OPERATION_EQUALS) {
        	String textAnswer = acumulator + "";
            answer.setText(textAnswer.contains(".0") ? (textAnswer.substring(0,textAnswer.length() - 2)) : textAnswer);
            acumulator = 0;
        }
    }
    
    private class AnswerLabel extends Label {
    	private int fontSize = 50;    	
    	

		public AnswerLabel() {
    		super("0", RIGHT, Color.BLACK, true);
    		this.borderColor = Color.BLUE;
            this.setFont(Font.getFont(fontSize));
            this.setInsets(0, UnitsConverter.toPixels(DP + 8), 0, 0);
    	}
    	
    	@Override
    	public void setText(String text) {
    		super.setText(text);
    		if(fm.stringWidth(this.getText()) >= this.getWidth() && this.getWidth() != 0) {
    			while(fm.stringWidth(this.getText()) >= this.getWidth() && fontSize > 26) {
    				fontSize -= 12 ;
    				this.setFont(Font.getFont(fontSize));
    			}
    		}
    	}
    	
		public void resetFontSize() {
			this.fontSize = 50;
			this.setFont(Font.getFont(fontSize));
		}    	
    }

    private class OperatorInputButton extends Button implements PressListener {
        private final int operation;

        private OperatorInputButton(int operation, String text) {
        	super(text);
        	this.operation = operation;
            this.setBackColor(0xf6cb43);
            this.addPressListener(this);
        }

        @Override
        public void controlPressed(ControlEvent e) {
            operatorInput(operation);
            calcOperation = operation;
        }
    }

    private class NumberInputButton extends Button implements PressListener {
        private final int number;

        private NumberInputButton(int number) {
            super(number + "");
            this.number = number;
            this.addPressListener(this);
        }

        @Override
        public void controlPressed(ControlEvent e) {
        	if(acumulator == 0 && value.equals("0")) {
        		clearScreen(false);
        	}
        	if (value.equals("0")) {
        		value = number + "";
        	} else {
        		value += number;
        	}
        	answer.setText(value);
        }
    }

    private class SpecialButton extends Button implements PressListener {
        private final int operation;

        private SpecialButton(String text, int operation) {
            super(text);
            this.operation = operation;
            this.addPressListener(this);
        }

        @Override
        public void controlPressed(ControlEvent e) {
            switch (operation) {
            	case SPECIAL_OPERATION_ERASE:
            		if (value.length() > 1) {
            			value = value.substring(0, value.length() - 1);
            		} else {
            			value = "0";
            		}
            		
            		answer.setText(value);
            	break;
                case SPECIAL_OPERATION_CLEAR:
                    clearScreen(true);
                    break;
                case SPECIAL_OPERATION_CHANGE_SIGN:
                	if (value.charAt(0) == '-') {
                		value = value.substring(1);
                	} else {
                		value = '-' + value;
                	}
                	answer.setText(value);
                    break;
                case SPECIAL_OPERATION_PERCENTAGE:
                    double parse = Double.parseDouble(value);
                    parse /= 100;
                    value = parse + "";
                    operatorInput(OPERATION_TIMES);
                    calcOperation = OPERATION_TIMES;
                    break;
                case SPECIAL_OPERATION_DOT:
                	if (!value.contains(".")) {
                		value += ".";
                	}
                	answer.setText(value);
                	break;
            }
        }
    }
}