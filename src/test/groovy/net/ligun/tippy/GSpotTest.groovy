package net.ligun.tippy

import spock.lang.*

class GSpotSpec extends Specification {
  def "命令を解釈できる"() {
    when:
      def programData = '><+-,.[]'
      
    then:
      def gspot = new GSpot(programData:programData)
  }
  
  def "解釈できない文字列でIllegalArgumentExceptionをスローする"() {
    when:
      def programData = 'abcdefg'
      def gspot = new GSpot(programData:programData)
      
    then:
      thrown(IllegalArgumentException)
  }
  
  def "Hello World"() {
    when:
      def program = '+++++++++[>++++++++>+++++++++++>+++++<<<-]>.>++.+++++++..+++.>-.------------.<++++++++.--------.+++.------.--------.>+.'
      def baos = new ByteArrayOutputStream()
      def gspot = new GSpot(programData:program, output:baos)
      
    then:
      gspot.run()
      baos.toString() == 'Hello, world!'
      
  }
}
