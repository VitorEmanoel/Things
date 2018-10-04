# Things
Things I do when someone asks, that's all.\
In case you want to add anything here make a Pull Request that I will analyze it and maybe accept it.<br/>
<br/>

### Permission
Make an enumerated system of permissions that can be saved to a database using only a number!
```java
Permissioned permissioned = () -> Arrays.asList(
  Permission.WRITE,
  Permission.ADD,
  Permission.DELETE
);

int val = permissioned.getPermissionsVal();
System.out.println("Permissions: " + prettyBuild(permissioned.getPermissions().toArray(new Permission[0]), Enum::name, ",", "and"));
System.out.println("Value: " + val);

for(Permission p : Permission.values()) {
  System.out.println("Has permission " + p.name() + "?: " + Permission.hasVal(p.id, val));
}
```
```
Permissions: WRITE, ADD and DELETE
Value (save this in database): 14
Has permission READ?: false
Has permission WRITE?: true
Has permission DELETE?: true
Has permission ADD?: true
```

***
### Step
In Kotlin there are some things that Java does not have that are extremely useful.\
One of them is step, until, and other functions, this class adds some of these functions in a simplified and functional way.<br/>
```java
Step.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) // collection or array
                .in(0, 10) // AKA between
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
```
```
Index (loop: 8, original: 0)
To infinity and beyond!
Index (loop: 3, original: 1)
Index (loop: 4, original: 2)
Index (loop: 5, original: 3)
They cut my cheap = (
```
***

### Time
Format your milliseconds in a way that any human can read through.<br/>
```java
long millis = 1000;
Random r = new Random();
for(int j = 0; j < 10; j++) {
    System.out.println("We have: " + Time.of(millis)
                    .sec(i -> i + " seconds")
                    .min(i -> i + " minutes")
                    .hour(i -> i + " hours")
                    .day(i -> i + " days")
                    .get());
    for(int k = 0; k < 5; k++) {
        millis += TimeUnit.MINUTES.toMillis(r.nextInt(60));
    }
    millis += TimeUnit.DAYS.toMillis(1);
}
```
```
We have: 1 seconds 
We have: 1 seconds, 32 minutes, 2 hours and 1 days
We have: 1 seconds, 6 minutes, 4 hours and 2 days
We have: 1 seconds, 12 minutes, 6 hours and 3 days
We have: 1 seconds, 45 minutes, 8 hours and 4 days
We have: 1 seconds, 33 minutes, 10 hours and 5 days
We have: 1 seconds, 10 minutes, 12 hours and 6 days
We have: 1 seconds, 55 minutes, 14 hours and 7 days
We have: 1 seconds, 53 minutes, 17 hours and 8 days
We have: 1 seconds, 8 minutes, 20 hours and 9 days
```
