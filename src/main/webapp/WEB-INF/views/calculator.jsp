<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
    <link rel="stylesheet" href="/test-mvn-app/resources/css/calculator.css"/>
</head>
<body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script>
    var lastValue;
    var LastOperation;
    var LastSimbol;

    function addSymbol(buttonValue){
        console.log("adding simbol"+ buttonValue);
        if(LastSimbol===null){clearInput();}
        document.getElementById('calc_value').value +=buttonValue;
        LastSimbol=undefined;
    }
    function clearInput(){
        document.getElementById('calc_value').value ='';
    }
    function calculate(){
        var curValue = parseInt(document.getElementById('calc_value').value);
        var lValue = parseInt(lastValue);
        var result;
        console.log(curValue, lastOperation, lValue);
        if(lastOperation==='+'){
            result = lValue+curValue;
        }
        else if(lastOperation==='-'){

            result = lValue-curValue;
        }
        else if(lastOperation==='*'){
            result = lValue*curValue;
        }
        else if(lastOperation==='/'){
            if(curValue===0){
                alert("Результат деления на ноль стремится к бесконечности")
            }
            result = lValue/curValue;
        }

        else {alert("Результат получлся лажей")}
        document.getElementById('calc_value').value = result;
        LastSimbol=null;

    }
    function addOperation(buttonValue){
        lastValue= document.getElementById('calc_value').value;
        clearInput();
        lastOperation=buttonValue;
    }
    function weather() {
        $.get( "weather",
                function(data) {
                    document.getElementById('temperature_value').value
                            =  data.temperature;
                }
        );
    }
    function toUSD() {
        $.get( "http://api.fixer.io/latest",
                function(data) {
                    document.getElementById('calc_value').value
                            = document.getElementById('calc_value').value
                            * data.rates.USD;
                }
        );
    }


</script>
<div class="calculator">
    <div>Калькулятор</div>

        <input id="calc_value" type="text">
            <div class="keys">
                <p><input type="button" value="/" onclick="addOperation('/');">
                <input type="button" value="7" onclick="addSymbol('7');">
                <input type="button" value="8" onclick="addSymbol('8');">
                <input type="button" value="9" onclick="addSymbol('9');"></p>
                <p><input type="button" value="*" onclick="addOperation('*');">
                <input type="button" value="4" onclick="addSymbol('4');">
                <input type="button" value="5" onclick="addSymbol('5');">
                <input type="button" value="6" onclick="addSymbol('6');"></p>
                <p><input type="button" value="-" onclick="addOperation('-');">
                <input type="button" value="1" onclick="addSymbol('1');">
                <input type="button" value="2" onclick="addSymbol('2');">
                <input type="button" value="3" onclick="addSymbol('3');"></p>
                <p><input type="button" value="+" onclick="addOperation('+');">
                <input type="button" value="0" onclick="addSymbol('0');">
                <input type="button" value="." onclick="addSymbol('.');">
                <input type="button" value="=" onclick="calculate();"></p>
                <input type="button" value="c" onclick="clearInput();">
                <input type="button" onclick="toUSD();" value="toUSD"/>
                <input id="temperature_value" type="text">
                <input type="button" onclick="weather();" value="weather"/>

            </div>

    </div>
</div>

</body>
</html>