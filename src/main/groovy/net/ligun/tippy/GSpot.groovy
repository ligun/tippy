package net.ligun.tippy

public class GSpot {
  protected List<Byte> data = [0x00]
  protected Integer ptr = 0
  protected List<Token> program = []
  protected int pc = 0
  protected List<Integer> jump_addr = new LinkedList<Integer>()
  protected InputStream is
  protected OutputStream os
  protected def instructionMap
  protected static Map TOKEN_LIST = [:]
  
  static {
    TOKEN_LIST[Token.NEXT] = '>'
    TOKEN_LIST[Token.PREVIOUS] = '<'
    TOKEN_LIST[Token.INC] = '+'
    TOKEN_LIST[Token.DEC] = '-'
    TOKEN_LIST[Token.GET] = ','
    TOKEN_LIST[Token.PUT] = '.'
    TOKEN_LIST[Token.LOOP] = '['
    TOKEN_LIST[Token.JUMP] = ']'
  }
  
  public GSpot(param) {
    compile(param.programData)
    this.is = param.input?:System.in
    this.os = param.output?:System.out

    Token.NEXT.metaClass.interpret { ++ptr }
    Token.PREVIOUS.metaClass.interpret { --ptr }
    Token.INC.metaClass.interpret { data[ptr] = data[ptr]? data[ptr]+1: 1 }
    Token.DEC.metaClass.interpret { --data[ptr] }
    Token.GET.metaClass.interpret { data[ptr] = is.read() }
    Token.PUT.metaClass.interpret { os.write(data[ptr]); os.flush() }
    Token.LOOP.metaClass.interpret {
      if(data[ptr] == 0) {
        for(int i in pc..program.size()){
          if(program[i] == Token.JUMP) {
            pc = i
            break
          }
        }
      }else {
        jump_addr.push(pc)
      }
    }
    Token.JUMP.metaClass.interpret { pc = jump_addr.pop() - 1 }
  }
  
  public void run() {
    while(program[pc] != null) {
      program[pc].interpret()
      ++pc
    }
  }
  
  protected void compile(String s) {
    if(s.length() == 0) { return }
    try {
      Token token = TOKEN_LIST.find{s.startsWith(it.value)}.key
      program.add(token)
      compile(s.substring(TOKEN_LIST[token].length(),s.length()))
    }catch(NullPointerException e) {
      throw new IllegalArgumentException("${s} can't be interpreted.")
    }
  }
  
  protected enum Token {
    NEXT,
    PREVIOUS,
    INC,
    DEC,
    GET,
    PUT,
    LOOP,
    JUMP,
  }
}
