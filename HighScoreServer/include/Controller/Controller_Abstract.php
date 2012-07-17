<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
abstract class Controller_Abstract
{
    protected $_request;

    public function __construct(Request $request){
        $this->_request = $request;
    }

    public function render($file){
        if(!file_exists( "./View/" . ucfirst($this->_request->getController()) . "/" . $file . ".phtml")){
             throw new Exception("File does not exist");
        }

        include("./View/" . ucfirst($this->_request->getController()) . "/" . $file . ".phtml");

        /*$doc = new DOMDocument();
        $doc->loadHTMLFile("./View/" . ucfirst($this->_request->getController()) . "/" . $file . ".phtml");
        echo $doc->saveHTML(); */

        //$data = file_get_contents( "./View/" . ucfirst($this->_request->getController()) . "/" . $file . ".phtml");
        //echo $data;
    }

}
