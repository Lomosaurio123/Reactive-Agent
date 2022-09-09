# Reactive-Agent
 This is a project for Fundamentals of Artificial Intelligence where I create a Reactive Agent to collect pokeballs and get back to the house.

# Explanation:

## Important:

| Objects  |      Matrix   |
|----------|:-------------:|
|          |       1       |
|          |       2       |
|          |       3       |

## Obstacle mechanics:
The first part is to deal with diagonal movement, so I use a while loop until to find that the movement between row and columns are different, then to make the movement randomly I use a function that returns random numbers in the range of -1 to 1.

![image](https://user-images.githubusercontent.com/85517698/189185020-dd37aadc-b70f-4243-b602-ad7feaf36d28.png)

Now, we have to check when we are in the border of the table or if we find an obstacle, so we have to make sure that we aren't going to move to that row or column.

![image](https://user-images.githubusercontent.com/85517698/189185864-9e249c51-cb75-4a02-8fd1-7155e2f9fea6.png)

![image](https://user-images.githubusercontent.com/85517698/189187051-9ccadf1d-9968-480a-a28d-8a505f7e77a4.png)

Finally we have to deal with the edge case when the agent is between obstacles or between an obstacle and the border.

![image](https://user-images.githubusercontent.com/85517698/189187415-64449045-0dfa-48d6-b36b-a0e0eb1a2ff1.png)

![image](https://user-images.githubusercontent.com/85517698/189187739-987fcabb-d4bf-4bd3-9861-261a16ddd4d7.png)

after check all the cases we do the movement on the table.

![image](https://user-images.githubusercontent.com/85517698/189187905-7af96534-1bbf-4f38-b3f0-082c91b2b4fd.png)
