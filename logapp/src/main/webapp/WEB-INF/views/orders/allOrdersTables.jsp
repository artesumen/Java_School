<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-10">
    <link href="../webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script scr="../webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script scr="../webjars/jquery/3.0.0/jquery.js"></script>
    <title>All orders</title>
</head>
<body>

<c:forEach items="${orders}" var="order">
<table class="table table-striped">
    <h3>Order #${order.orderId}</h3>
    <p>Order status:
        <c:choose>
            <c:when test="${order.orderIsDone==true}">
                Completed
            </c:when>
            <c:otherwise>
                Not completed
            </c:otherwise>
        </c:choose>
    </p>
    <p>Assigned drivers:
        <br>
        <c:forEach items="${order.driversOnOrderIds}" var="driverId">

            ${driverService.getDriverById(driverId).driverPrivateNum}:
            ${driverService.getDriverById(driverId).driverFirstName}
            ${driverService.getDriverById(driverId).driverSurname}
            <br>
        </c:forEach></p>
    <p>Assigned truck:
        <c:choose>
            <c:when test="${order.truckId!=0}">
                ${truckMap.get(order.truckId)}
            </c:when>
            <c:otherwise>
                No truck is assigned yet
            </c:otherwise>
        </c:choose>
    </p>

    <table class="table table-striped">
        <th scope="col">Cargo code</th>
        <th scope="col">Cargo title</th>
        <th scope="col">Cargo weight kilos</th>
        <th scope="col">Cargo current city</th>
        <th scope="col">Cargo destination city</th>
        <th scope="col">Cargo status</th>

        <c:forEach items="${order.points}" var="point">
            <tr>
                <td>${point.cargo.cargoName}</td>
                <td>${point.cargo.title}</td>
                <td>${point.cargo.cargoWeightKilos}</td>
                <td>${point.cargo.currentCity.cityName}</td>
                <td>${point.destCity.cityName}</td>
                <td>${point.cargo.cargoStatus}</td>
            </tr>


        </c:forEach>
    </table>


                <c:if test="${order.truckId==null}">
                    <a href="<c:url value='./readyToGoTrucks/${order.orderId}' />">Get ready trucks</a><br>
                </c:if>
                <c:if test="${order.truckId!=null}">
                    <a href="<c:url value='./readyForTripDrivers/${order.orderId}' />">Get ready drivers</a><br>
                </c:if>
                <a href="<c:url value='./notAssignedCargoes/${order.orderId}' />">Get Cargoes to assign</a><br>
                    <%--        <a class="btn btn-danger" href="<c:url value='/removeOrder/${order.orderId}' />" role="button">Delete order</a>--%>
                <br>
        <%--        <th scope="col">Order Id</th>--%>
        <%--        <th scope="col">Point</th>--%>
        <%--        <th scope="col">City</th>--%>
        <%--        <th scope="col">Status</th>--%>
        <%--        <th scope="col">Assigned drivers</th>--%>
        <%--        <th scope="col">Assigned truck</th>--%>
        <%--        <c:if test="${!empty orders}">--%>


        <%--            <c:forEach items="${order.points}" var="point">--%>
        <%--                <tr>--%>

        <%--                    <td>${order.orderId}</td>--%>


        <%--                    <td>${point.name}</td>--%>
        <%--                    <td>${point.destCity.cityName}</td>--%>

        <%--                    <c:choose>--%>
        <%--                        <c:when test="${order.orderIsDone==true}">--%>
        <%--                            <td>Completed</td>--%>
        <%--                        </c:when>--%>
        <%--                        <c:otherwise>--%>
        <%--                            <td>Not completed</td>--%>
        <%--                        </c:otherwise>--%>
        <%--                    </c:choose>--%>
        <%--                    <td>--%>
        <%--                        <c:forEach items="${order.driversOnOrderIds}" var="driverId">--%>

        <%--                            ${driverService.getDriverById(driverId).driverSurname}--%>
        <%--                        </c:forEach>--%>
        <%--                    </td>--%>
        <%--                    <td>${point.assignedTruck.regNumber} </td>--%>


        <%--                </tr>--%>
        <%--            </c:forEach>--%>
        <%--        </c:if>--%>


        <%--    </table>--%>
    </c:forEach>

    <a class="btn btn-primary" href="/" role="button">Main page</a>
    <form action="${pageContext.request.contextPath}/orders/addOrder" method="post">

        <input type="submit" value="Add Empty Order"/>
    </form>


</body>
</html>
