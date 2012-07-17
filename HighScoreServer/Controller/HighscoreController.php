<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */

require_once("./include/Controller/Controller_Abstract.php");
require_once("./Model/Highscore/Model_Highscore_DbMapper.php");

class HighscoreController extends Controller_Abstract
{
    public function storeAction() {
        try {
            if(!is_numeric($this->_request->getParam("score"))) {
                throw new Exception ("Bad Parameter Score");
            }

            if(!is_string($this->_request->getParam("username"))) {
                throw new Exception ("Bad Parameter Username");
            }

           // $this->_db->setScore($this->_request->getParams()["score"], $this->_request->getParams()["username"]);
            $mapper = new Model_Highscore_DbMapper();
            $mapper->setNewHighscore($this->_request->getParam("username"), $this->_request->getParam("score"));

        } catch (Exception $e) {
            echo $e->getMessage();
        }
    }

    public function loadAction() {

        try {
            if(!is_numeric($this->_request->getParam("count"))) {
                throw new Exception ("Bad Parameter Count");
            }

            //$this->_db->getScore($this->_request->getParams()["count"]);
            $mapper = new Model_Highscore_DbMapper();
            $result = $mapper->getSomeHighscores($this->_request->getParam("count"));
            $this->result =  json_encode($mapper->toArray($result));
            $this->render("load");

        } catch (Exception $e) {
            echo $e->getMessage();
        }
    }
}