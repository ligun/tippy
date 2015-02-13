package net.ligun.tippy

import spock.lang.*

class TippySpec extends Specification {
  def "命令を解釈できる"() {
    when:
      def programData = 'ぴょんつーんだふわどきまち？こころここあちの'
      
    then:
      def tippy = new Tippy(programData:programData)
  }
  
  def "解釈できない文字列でIllegalArgumentExceptionをスローする"() {
    when:
      def programData = 'りぜかわいい'
      def tippy = new Tippy(programData:programData)
      
    then:
      thrown(IllegalArgumentException)
  }
  
  def "Hello World"() {
    when:
      def program = 'ぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんここあふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんふわぴょんぴょんぴょんぴょんぴょんどきどきどきつーんだちのふわこころふわぴょんぴょんこころぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころこころぴょんぴょんぴょんこころふわつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころどきぴょんぴょんぴょんぴょんぴょんぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころぴょんぴょんぴょんこころつーんだつーんだつーんだつーんだつーんだつーんだこころつーんだつーんだつーんだつーんだつーんだつーんだつーんだつーんだこころふわぴょんこころ'
      def baos = new ByteArrayOutputStream()
      def tippy = new Tippy(programData:program, output:baos)
      
    then:
      tippy.run()
      baos.toString() == 'Hello, world!'
      
  }
}
