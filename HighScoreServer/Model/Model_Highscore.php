<?php
/**
 * Created by JetBrains PhpStorm.
 * User: pascal
 * Date: 08.07.12
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
class Model_Highscore
{
    private $_Id;
    private $_Score;
    private $_Username;


    public function setId($Id)
    {
        $this->_Id = $Id;
    }

    public function getId()
    {
        return $this->_Id;
    }

    public function setScore($Score)
    {
        $this->_Score = $Score;
    }

    public function getScore()
    {
        return $this->_Score;
    }

    public function setUsername($Username)
    {
        $this->_Username = $Username;
    }

    public function getUsername()
    {
        return $this->_Username;
    }
}