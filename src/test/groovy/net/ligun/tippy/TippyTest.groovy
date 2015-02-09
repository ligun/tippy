package net.ligun.tippy

import spock.lang.*

class TippySpec extends Specification {
  def "命令を解釈できる"() {
    when:
      def strProgram = 'ぴょんつーんだふわどきいつもこころここあちの'
      
    then:
      def tippy = new Tippy(strProgram)
  }
  
  def "解釈できない文字列でIllegalArgumentExceptionをスローする"() {
    when:
      def strProgram = 'りぜかわいい'
      def tippy = new Tippy(strProgram)
      
    then:
      thrown(IllegalArgumentException)
  }
  
  def "Hello World"() {
    when:
      def program = 'ぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんここあふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんどきどきどきつーんだちのふわこころふわぴょんぴょんこころぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころこころぴょんぴょんぴょんこころふわつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころどきぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころふわぴょんこころ'
      def baos = new ByteArrayOutputStream()
      def tippy = new Tippy(program, System.in, baos)
      
    then:
      tippy.run()
      baos.toString() == 'Hello, world!'
      
  }
}
