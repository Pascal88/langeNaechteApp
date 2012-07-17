<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 05.07.12
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

//set_include_path("./include/");
define('BASE_PATH', realpath(dirname(__FILE__) . '/../'));

set_include_path(
    BASE_PATH . '/include'
        . PATH_SEPARATOR . get_include_path()
);

require_once("./include/Router.php");
require_once("./include/Dispatcher.php");

/*function __autoload($class_name) {

    if(false === strpos($class_name, "_") && false === strpos($class_name, "Controller") && false === strpos($class_name, "Model")) {
        include "./include/".ucfirst($class_name).".php";

    } elseif (false !== strpos($class_name, "Controller") && false === strpos($class_name, "Abstract") && false === strpos($class_name, "Interface")) {
        include "./Controller/". ucfirst($class_name)."php";

    } elseif (false !== strpos($class_name, "Model") && false === strpos($class_name, "Abstract") && false === strpos($class_name, "Interface")) {
        include BASE_PATH."/Model/". ucfirst($class_name)."php";

    } else {
        include BASE_PATH."/include/".substr($class_name,0,strpos($class_name, "_"))."/".ucfirst($class_name).".php";
    }
}      */

$router = new Router();
$request = $router->getRequest();
$dispatcher = new Dispatcher($request);
$dispatcher->dispatch();