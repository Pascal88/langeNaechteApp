<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */

require_once("Request.php");

class Router
{
    private $_request;
    public function __construct() {
        $this->_request = new Request();

        if(!empty($_REQUEST['controller'])) {
            $this->_request->setController($_REQUEST['controller']);
        }

        if(!empty($_REQUEST['action'])) {
            $this->_request->setAction($_REQUEST['action']);
        }

        foreach($_REQUEST as $key => $value) {
            if($key == "controller" || $key == "action") continue;
            $this->_request->addParam($key, $value);
        }
    }

    public function getRequest(){
        return $this->_request;
    }
}
