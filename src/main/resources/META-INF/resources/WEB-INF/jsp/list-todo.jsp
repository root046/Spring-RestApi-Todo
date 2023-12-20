<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>Welcome ${name}</h1>
    <hr>
    <h1>Your Todos</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ToDos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.isDone}</td>
                        <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">DELETE ${todo.id}</a></td>
                        <td><a href="update-todo?id=${todo.id}" class="btn btn-warning">UPDATE ${todo.id}</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add Todo</a>
    </div>
<%@ include file="common/header.jspf" %>