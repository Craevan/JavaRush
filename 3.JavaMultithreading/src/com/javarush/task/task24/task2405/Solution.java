package com.javarush.task.task24.task2405;

/* 
Black box
*/

public class Solution implements Action {
    public static int countActionObjects;

    private int param;

    private Action solutionAction = new Action() {
        //напишите тут ваш код
        private FirstClass firstClass;
        private SecondClass secondClass;

        public void someAction() {
            //напишите тут ваш код
            if (param > 0) {
                if (firstClass == null) {
                    firstClass = new FirstClass() {
                        @Override
                        public void someAction() {
                            super.someAction();
                            Solution.this.someAction();
                        }

                        @Override
                        public Action getDependantAction() {
                            System.out.println(param);
                            param--;
                            return param > 0 ? Solution.this : this;
                        }
                    };
                }
                firstClass.getDependantAction().someAction();
            }
            else {
                if (secondClass == null) {
                    secondClass = new SecondClass() {
                        @Override
                        public void someAction() {
                            sb.append(SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM).append(param);
                            super.someAction();

                        }
                    };
                }
                secondClass.someAction();
            }
        }
    };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}
