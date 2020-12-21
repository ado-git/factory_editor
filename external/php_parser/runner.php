<?php

class PHPCodeFormater
{
    protected $mapping = array();
    protected $itemClasses = array();
    
    public function __construct()
    {
        $this->itemClasses["keyword"] = 1;
        $this->itemClasses["opentag"] = 1;
		$this->itemClasses["closetag"] = 1;
        $this->itemClasses["variable"] = 1;
        $this->itemClasses["string"] = 1;
        $this->itemClasses["comment"] = 1;
        
        $this->mapping[T_ABSTRACT] = "keyword";
        $this->mapping[T_ARRAY] = "keyword";
        $this->mapping[T_AS] = "keyword";
        $this->mapping[T_BREAK] = "keyword";
        $this->mapping[T_CASE] = "keyword";
        $this->mapping[T_CATCH] = "keyword";
        $this->mapping[T_CLASS] = "keyword";
        $this->mapping[T_CLOSE_TAG] = "closetag";
        $this->mapping[T_COMMENT] = "comment";
        $this->mapping[T_CONSTANT_ENCAPSED_STRING] = "string";
        $this->mapping[T_CONTINUE] = "keyword";
        $this->mapping[T_DECLARE] = "keyword";
        $this->mapping[T_DEFAULT] = "keyword";
        $this->mapping[T_DO] = "keyword";
        $this->mapping[T_ECHO] = "keyword";
        $this->mapping[T_ELSE] = "keyword";
        $this->mapping[T_ELSEIF] = "keyword";
        $this->mapping[T_EMPTY] = "keyword";
        $this->mapping[T_ENDFOR] = "keyword";
        $this->mapping[T_ENDDECLARE] = "keyword";
        $this->mapping[T_ENDFOREACH] = "keyword";
        $this->mapping[T_ENDIF] = "keyword";
        $this->mapping[T_ENDSWITCH] = "keyword";
        $this->mapping[T_ENDWHILE] = "keyword";
        $this->mapping[T_EVAL] = "keyword";
        $this->mapping[T_EXIT] = "keyword";
        $this->mapping[T_EXTENDS] = "keyword";
        $this->mapping[T_FINAL] = "keyword";
        $this->mapping[T_FINALLY] = "keyword";
        $this->mapping[T_FOR] = "keyword";
        $this->mapping[T_FOREACH] = "keyword";
        $this->mapping[T_FUNCTION] = "keyword";
        $this->mapping[T_GLOBAL] = "keyword";
        $this->mapping[T_GOTO] = "keyword";
        $this->mapping[T_IF] = "keyword";
        $this->mapping[T_IMPLEMENTS] = "keyword";
        $this->mapping[T_INCLUDE] = "keyword";
        $this->mapping[T_INCLUDE_ONCE] = "keyword";
        $this->mapping[T_ISSET] = "keyword";
        $this->mapping[T_LIST] = "keyword";
        $this->mapping[T_NEW] = "keyword";
        $this->mapping[T_OPEN_TAG] = "opentag";
        $this->mapping[T_PRINT] = "keyword";
        $this->mapping[T_PRIVATE] = "keyword";
        $this->mapping[T_PUBLIC] = "keyword";
        $this->mapping[T_PROTECTED] = "keyword";
        $this->mapping[T_REQUIRE] = "keyword";
        $this->mapping[T_REQUIRE_ONCE] = "keyword";
        $this->mapping[T_RETURN] = "keyword";
        $this->mapping[T_STATIC] = "keyword";
        $this->mapping[T_SWITCH] = "keyword";
        $this->mapping[T_THROW] = "keyword";
        $this->mapping[T_TRY] = "keyword";
        $this->mapping[T_UNSET] = "keyword";
        $this->mapping[T_VARIABLE] = "variable";
        $this->mapping[T_WHILE] = "keyword";
        
    }
    
    protected function makeEntry($text, $classtoken = null)
    {   
        if(!isset($classtoken) || !isset($this->mapping[$classtoken]))
            return "otro<".htmlentities($text).">";
        
        $class = $this->mapping[$classtoken];
        
        return $class."<".htmlentities($text).">";
    }
    public function render($source)
    {
        $tokenArray = token_get_all($source);
        foreach($tokenArray as $token)
        {
            if(is_array($token))
            {
                echo $this->makeEntry($token[1],$token[0])."\n";
            }
            else
                echo $this->makeEntry($token)."\n";
        }
        
    }
}



$content = file_get_contents($argv[1]);

if(!$content)
	exit();

$formater = new PHPCodeFormater();

$formater->render($content);