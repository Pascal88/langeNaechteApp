<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
class Request
{
    private $controller;
    private $action;
    private $params = array();

    public function setAction($action)
    {
        $this->action = $action;
    }

    public function getAction()
    {
        return $this->action;
    }

    public function setController($controller)
    {
        $this->controller = $controller;
    }

    public function getController()
    {
        return $this->controller;
    }

    public function setParams($params)
    {
        $this->params = $params;
    }

    public function addParam($key, $value){
        $this->params[$key] = $value;
    }

    public function getParams()
    {
        return $this->params;
    }

    public function getParam($name) {
        return $this->params[$name];
    }
}
