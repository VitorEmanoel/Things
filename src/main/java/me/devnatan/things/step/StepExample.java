package me.devnatan.things.step;

public class StepExample {

    /*
        OUTPUT:

        Index (loop: 8, original: 0)
        To infinity and beyond!
        Index (loop: 3, original: 1)
        Index (loop: 4, original: 2)
        Index (loop: 5, original: 3)
        They cut my cheap = (

        Process finished with exit code 0
     */
    public static void main(String[] args) {
        Step.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) // collection or array
                // .in(0, 10) // AKA between
                .until(3, 4) // AKA ignore
                .step(2) // AKA jump
                .goTo(8) // AKA startAt
                .forEach(it -> {
                    System.out.println("Index (loop: " + it.index + ", original: " + it.originalIndex + ")");
                    /*
                        The variable "goTo" is dynamically changeable, while the other class variables are not.
                        You can change it independently if the process is running.
                        Changing it will cause the current position to change to the position defined in this variable.
                        You can use it directly in class construction to start in a specific position.
                        The variable returns to its original state shortly after it is used.
                        To remove it set its value to 0.
                     */

                    // INFINITE LOOP
                    if(it.index == 8) {
                        it.goTo = 2;
                        System.out.println("To infinity and beyond!");
                    }

                    if(it.index == 5) {
                        it.stop(); // STOP IT!
                        System.out.println("They cut my cheap = (");
                    }
                });
    }

}
