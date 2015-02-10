package net.ligun.tippy

import spock.lang.*

class GSpotSpec extends Specification {
  def "命令を解釈できる"() {
    when:
      def strProgram = 'ぴょんつーんだふわどきまち？こころここあちの'
      
    then:
      def gspot = new GSpot(strProgram)
  }
  
  def "解釈できない文字列でIllegalArgumentExceptionをスローする"() {
    when:
      def strProgram = 'りぜかわいい'
      def gspot = new GSpot(strProgram)
      
    then:
      thrown(IllegalArgumentException)
  }
  
  def "Hello World"() {
    when:
      def program = 'ぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんここあふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんどきどきどきつーんだちのふわこころふわぴょんぴょんこころぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころこころぴょんぴょんぴょんこころふわつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころどきぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころふわぴょんこころ'
      def baos = new ByteArrayOutputStream()
      def gspot = new GSpot(program, System.in, baos)
      
    then:
      gspot.run()
      baos.toString() == 'Hello, world!'
      
  }
}
