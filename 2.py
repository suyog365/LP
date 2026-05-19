heuristic = {
    'A': 10,
    'B': 8,
    'C': 5,
    'D': 7,
    'E': 3,
    'F': 6,
    'G': 0
}

graph = {
    'A': [('B', 6), ('F', 3)],
    'B': [('C', 3), ('D', 2)],
    'C': [('D', 1), ('E', 5)],
    'D': [('C', 1), ('E', 8)],
    'E': [('G', 5)],
    'F': [('G', 7)],
    'G': []
}

def astar(start, goal):

    open_list = [start]
    closed_list = []

    g_cost = {}
    g_cost[start] = 0

    parent = {}
    parent[start] = start

    while open_list:

        current = open_list[0]

        for node in open_list:
            if g_cost[node] + heuristic[node] < g_cost[current] + heuristic[current]:
                current = node

        if current == goal:
            path = []

            while parent[current] != current:
                path.append(current)
                current = parent[current]

            path.append(start)
            path.reverse()

            print("Shortest Path:", path)
            return

        open_list.remove(current)
        closed_list.append(current)

        for (neighbor, cost) in graph[current]:

            if neighbor not in open_list and neighbor not in closed_list:
                open_list.append(neighbor)

                parent[neighbor] = current
                g_cost[neighbor] = g_cost[current] + cost

            else:
                if g_cost[neighbor] > g_cost[current] + cost:
                    g_cost[neighbor] = g_cost[current] + cost
                    parent[neighbor] = current

                    if neighbor in closed_list:
                        closed_list.remove(neighbor)
                        open_list.append(neighbor)

    print("Path does not exist")

start = input("Enter Start Node: ")
goal = input("Enter Goal Node: ")

astar(start, goal)