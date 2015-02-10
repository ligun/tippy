package net.ligun.tippy

public class GSpot {
  private List<Token> data = new LinkedList<Byte>()
  private Iterator<Byte> ptr
  private Byte current
  private Boolean left
  private List<Token> program = new LinkedList<Token>()
  private Iterator<Token> pc
  private Token current_token
  private InputStream input
  private OutputStream output
  
  public GSpot(String program_string, InputStream input = System.in, OutputStream output = System.out) {
    compile(program_string)
    data.add(0x00)
    ptr = data.listIterator(0)
    pc = program.listIterator(0)
    this.input = input
    this.output = output
  }
  
  public void run() {
    current_token = pc.next()
    current = ptr.next()
    left = false
    while(current_token != null) {
      interpret(current_token)
    }
  }
  
  protected void compile(String s) {
    if(s.length() == 0) { return }
    Token token = Token.values().find{s.startsWith(it.getTokenString())}
    if(token == null) {
      throw new IllegalArgumentException("${s} can't be interpreted.")
    }
    program.add(token)
    compile(s.substring(token.getTokenString().length(),s.length()))
  }
  
  protected void interpret(Token token) {
    switch(token) {
      case Token.NEXT:
        next()
        break
        
      case Token.PREVIOUS:
        previous()
        break
        
      case Token.INC:
        increment()
        break
        
      case Token.DEC:
        decrement()
        break
        
      case Token.GET:
        get()
        break
        
      case Token.PUT:
        put()
        break
        
      case Token.LOOP:
        loop()
        break
      case Token.JUMP:
        jump()
        break
    }
    pc_next()
  }
  
  private void next() {
    if(!ptr.hasNext()) {
      ptr.add(0x00)
      previous()
    } 
    current = ptr.next()
    
    if(left) {
      current = ptr.next()
    }
    left = false
  }
  
  private void previous() {
    current = ptr.previous()
    
    if(!left) {
      current = ptr.previous()
    }
    left = true
  }
  
  private void increment() {
    ptr.set(++current)
  }
  
  private void decrement() {
    ptr.set(--current)
  }
  
  private void get() {
    ptr.set(input.read())
  }
  
  private void put() {
    output.write(current)
  }
  
  private void loop() {
    if(current == 0) {
      while(current_token != Token.JUMP) {
        pc_next()
      }
    }
  }
  
  private void jump() {
    while(current_token != Token.LOOP) {
      current_token = pc.previous()
    }
  }
  
  private void pc_next() {
    if(pc.hasNext()) {
      current_token = pc.next()
    } else {
      current_token = null
    }
  }
  
  private enum Token {
    NEXT('ふわ'),
    PREVIOUS('どき'),
    INC('ぴょん'),
    DEC('つーんだ'),
    GET('まち？'),
    PUT('こころ'),
    LOOP('ここあ'),
    JUMP('ちの'),
    
    private final String token_string
    
    private Token(final String token_string) {
      this.token_string = token_string
    }
    
    public String getTokenString() {
      return token_string
    }
    
    public static Token toToken(String token_string) {
      Token token = values().find{ it.toTokenString == token_string }
      
      if (token == null) {
        throw new IllegalArgumentException("Illegal token : ${token_string}")
      }
      
      return token
    }
  }
}
