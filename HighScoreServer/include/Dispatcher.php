<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

require_once("./Controller/HighscoreController.php");

class Dispatcher
{
    private $_request;
    private $_controller;
    private $_action;

    public function __construct(Request $request) {
        $this->_request = $request;
    }

    public function dispatch() {
        if(class_exists( ucfirst ($this->_request->getController()."Controller"))) {
           $this->_controller =  ucfirst( $this->_request->getController()."Controller");
        } else {
            throw new Exception("Bad Controller");
        }

        $reflectionController = new ReflectionClass($this->_controller);

        if($reflectionController->hasMethod($this->_request->getAction()."Action")) {
            $this->_action = $this->_request->getAction()."Action";
        } else {
            throw new Exception ("Bad Action");
        }

        try {
            $controller = new $this->_controller($this->_request);
            $action =  $this->_action;
            $controller->$action();
        } catch(Exception $e){
            $e->getMessage();
        }
    }
}
