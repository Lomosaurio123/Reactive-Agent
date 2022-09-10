# Reactive-Agent
 This is a project for Fundamentals of Artificial Intelligence where I create a Reactive Agent to collect pokeballs and get back to the house.

# Explanation:

## Important:

| Objects   |      Matrix   |
|---------- |:-------------:|
| ![image](https://user-images.githubusercontent.com/85517698/189399373-0d0a59b0-d0c7-4bc1-96c1-6ff077beabb6.png) |       1       |
| ![image](https://user-images.githubusercontent.com/85517698/189399461-05010c03-0fb7-4709-a933-13a1325c48fe.png) |       2       |
| ![image](https://user-images.githubusercontent.com/85517698/189399512-18d89499-0e03-46c7-9b12-668fa1b1e959.png) |       3       |

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

## House mechanics

It's important to keep the icon of the house when any agent pass in that position, so we have to validate that case.

![image](https://user-images.githubusercontent.com/85517698/189503473-9577c801-bb32-4f1e-9ac8-612911210274.png)

- First: keep the previous row and column movement

![image](https://user-images.githubusercontent.com/85517698/189503500-606c8863-280a-48ec-9199-3b187c86e01e.png)

- Then after the agent pass to another position we return the icon.

## Recolection and Delivery mechanics

- The first thing that we have to do is save how many pokeballs the agents have to collect

![image](https://user-images.githubusercontent.com/85517698/189503580-0e43dc81-6dfa-463c-9d73-082f6e5aaf4f.png)
![image](https://user-images.githubusercontent.com/85517698/189503589-d01a2ded-1969-4576-adc8-e16fa9a8b8cd.png)

- We give the objective to the agents 

![image](https://user-images.githubusercontent.com/85517698/189503617-020155ef-890d-4597-87e5-e72de811bcbb.png)

- Now all agents must count how many pokeballs were collected.

![image](https://user-images.githubusercontent.com/85517698/189503640-e165d5ff-331a-4962-9e6a-e972b679650c.png)

- Now we check if there is a pokeball and add to the collected propierty and *return 0 the value of that position on the matrix*.

Finally we have to add this propierties to the agents.

![image](https://user-images.githubusercontent.com/85517698/189503755-9af37ff7-ae33-4195-81d3-78a2ad987176.png)
![image](https://user-images.githubusercontent.com/85517698/189503784-c6cace2d-d08c-4f44-b127-eb7c11d354cb.png)

- We are adding the partner because they have to know when to stop.

![image](https://user-images.githubusercontent.com/85517698/189503812-7fa80eef-6609-460d-8de1-bac77fdb12a4.png)

- When the agents found a house we increase the value of deliver and decrease the value of collected.

*FINALLY*

![image](https://user-images.githubusercontent.com/85517698/189504093-1d3d1a31-d666-4df7-bffa-0e529d6c5c2b.png)

- When all the agents end to deliver and the number of delivery is equal to the objective we end the process.
