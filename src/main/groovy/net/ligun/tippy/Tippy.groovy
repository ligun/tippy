package net.ligun.tippy

public class Tippy extends GSpot {
  static {
    TOKEN_LIST[Token.NEXT] = 'ふわ'
    TOKEN_LIST[Token.PREVIOUS] = 'どき'
    TOKEN_LIST[Token.INC] = 'ぴょん'
    TOKEN_LIST[Token.DEC] = 'つーんだ'
    TOKEN_LIST[Token.GET] = 'まち？'
    TOKEN_LIST[Token.PUT] = 'こころ'
    TOKEN_LIST[Token.LOOP] = 'ここあ'
    TOKEN_LIST[Token.JUMP] = 'ちの'
  }
  
  public Tippy(param) {
    super(param)
  }
}