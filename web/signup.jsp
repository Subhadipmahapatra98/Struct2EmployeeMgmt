<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.104.2">
        <title>Sign in - Employee Management</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <meta name="theme-color" content="#712cf9">


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">
<script>
function val() {
signupForm.submit();
}
</script>
    </head>

    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <form action=""  method="post" id="signupForm">
                <img class="mb-4" src="images/image.png" alt="exavalu logo" width="180" height="100">
                <h1 class="h3 mb-3 fw-normal">Create Account</h1>

                <div class="form-floating">
                    <input type="emailAddress" class="form-control" id="floatingInput" placeholder="name@example.com" name="emailAddress" value="${User.emailAddress}">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" value="${User.password}">
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="firstName" placeholder="first name" name="firstName" value="${User.firstName}">
                    <label for="firstName">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="lastName" placeholder="last name" name="lastName" value="${User.lastName}">
                    <label for="firstName">Last Name</label>
                </div>
                   <div class="form-floating">
                    <select name="countryId" class="form-select" id="countryId" onchange="val()">
                        <option value="0">Select a country</option>
                        <c:forEach var="country" items="${CountryList}">
                            <option value= ${country.getCountryId()}<c:if test="${country.getCountryId()==User.getCountryId()}"> selected </c:if>>  ${country.getCountryName()}  </option>
                      </c:forEach>
                    </select>                         
                </div>

                <div class="form-floating">
                    <select name="stateId" class="form-select" id="stateId" onchange="val()">
                        <option value="0">Select a state</option>
                      <c:forEach var="state" items="${StateList}">
                            <option value= ${state.getStateId()}  <c:if test="${state.getStateId()==User.getStateId()}"> selected </c:if>>  ${state.getStateName()}  </option>
                      </c:forEach>
                    </select>
                </div>
                <div class="form-floating">
                    <select name="districtId" class="form-select" id="districtId" >
                        <option value="0">Select a city</option>
                        <c:forEach var="district" items="${DistrictList}">
                            <option value= ${district.getDistrictId()} <c:if test="${district.getDistrictId()==User.getDistrictId()}"> selected </c:if>>  ${district.getDistrictName()}  </option>
                      </c:forEach>
                      
                    </select>
                </div>

                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="w-100 btn mt-2 btn-sm btn-primary" style="background-color: blue; border: none" type="submit">Sign up</button>
                <a href="landingPage.jsp">
                    <button type="button" class = "w-100 btn mt-2 btn-sm btn-secondary" style="background-color: blueviolet" type="submit">Cancel</button>
                </a>
                <p class="mt-5 mb-3 text-muted">&copy; 2017@2023</p>
            </form>
        </main>



    </body>
</html>
