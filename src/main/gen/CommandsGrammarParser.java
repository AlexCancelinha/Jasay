// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CommandsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Parameter=2, TypeToCreate=3, NavigationCommandKeyword=4, ClassCommandKeyword=5, 
		PrivacyKeyword=6, StaticKeyword=7, FinalKeyword=8, TypeKeyword=9, Text=10, 
		Numbers=11;
	public static final int
		RULE_classCommand = 0, RULE_classCommandSetterAndGetter = 1, RULE_addParameterInMethod = 2, 
		RULE_navigationCommandSimple = 3, RULE_name = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"classCommand", "classCommandSetterAndGetter", "addParameterInMethod", 
			"navigationCommandSimple", "name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", null, null, null, null, null, "'static'", "'final'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Parameter", "TypeToCreate", "NavigationCommandKeyword", 
			"ClassCommandKeyword", "PrivacyKeyword", "StaticKeyword", "FinalKeyword", 
			"TypeKeyword", "Text", "Numbers"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CommandsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommandsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassCommandContext extends ParserRuleContext {
		public TerminalNode ClassCommandKeyword() { return getToken(CommandsGrammarParser.ClassCommandKeyword, 0); }
		public TerminalNode TypeKeyword() { return getToken(CommandsGrammarParser.TypeKeyword, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<TerminalNode> Text() { return getTokens(CommandsGrammarParser.Text); }
		public TerminalNode Text(int i) {
			return getToken(CommandsGrammarParser.Text, i);
		}
		public TerminalNode PrivacyKeyword() { return getToken(CommandsGrammarParser.PrivacyKeyword, 0); }
		public TerminalNode StaticKeyword() { return getToken(CommandsGrammarParser.StaticKeyword, 0); }
		public TerminalNode FinalKeyword() { return getToken(CommandsGrammarParser.FinalKeyword, 0); }
		public ClassCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).enterClassCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).exitClassCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsGrammarVisitor ) return ((CommandsGrammarVisitor<? extends T>)visitor).visitClassCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassCommandContext classCommand() throws RecognitionException {
		ClassCommandContext _localctx = new ClassCommandContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_classCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Text) {
				{
				{
				setState(10);
				match(Text);
				setState(11);
				match(T__0);
				}
				}
				setState(16);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(17);
			match(ClassCommandKeyword);
			setState(20);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(18);
				match(T__0);
				setState(19);
				match(PrivacyKeyword);
				}
				break;
			}
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(22);
				match(T__0);
				setState(23);
				match(StaticKeyword);
				}
				break;
			}
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(26);
				match(T__0);
				setState(27);
				match(FinalKeyword);
				}
				break;
			}
			setState(30);
			match(T__0);
			setState(31);
			match(TypeKeyword);
			setState(32);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassCommandSetterAndGetterContext extends ParserRuleContext {
		public TerminalNode TypeToCreate() { return getToken(CommandsGrammarParser.TypeToCreate, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<TerminalNode> Text() { return getTokens(CommandsGrammarParser.Text); }
		public TerminalNode Text(int i) {
			return getToken(CommandsGrammarParser.Text, i);
		}
		public ClassCommandSetterAndGetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCommandSetterAndGetter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).enterClassCommandSetterAndGetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).exitClassCommandSetterAndGetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsGrammarVisitor ) return ((CommandsGrammarVisitor<? extends T>)visitor).visitClassCommandSetterAndGetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassCommandSetterAndGetterContext classCommandSetterAndGetter() throws RecognitionException {
		ClassCommandSetterAndGetterContext _localctx = new ClassCommandSetterAndGetterContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classCommandSetterAndGetter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Text) {
				{
				{
				setState(34);
				match(Text);
				setState(35);
				match(T__0);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			match(TypeToCreate);
			setState(42);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddParameterInMethodContext extends ParserRuleContext {
		public TerminalNode TypeKeyword() { return getToken(CommandsGrammarParser.TypeKeyword, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode Parameter() { return getToken(CommandsGrammarParser.Parameter, 0); }
		public List<TerminalNode> Text() { return getTokens(CommandsGrammarParser.Text); }
		public TerminalNode Text(int i) {
			return getToken(CommandsGrammarParser.Text, i);
		}
		public AddParameterInMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addParameterInMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).enterAddParameterInMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).exitAddParameterInMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsGrammarVisitor ) return ((CommandsGrammarVisitor<? extends T>)visitor).visitAddParameterInMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddParameterInMethodContext addParameterInMethod() throws RecognitionException {
		AddParameterInMethodContext _localctx = new AddParameterInMethodContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_addParameterInMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Text) {
				{
				{
				setState(44);
				match(Text);
				setState(45);
				match(T__0);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(51);
			match(Parameter);
			setState(52);
			match(T__0);
			}
			setState(54);
			match(TypeKeyword);
			setState(55);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NavigationCommandSimpleContext extends ParserRuleContext {
		public TerminalNode NavigationCommandKeyword() { return getToken(CommandsGrammarParser.NavigationCommandKeyword, 0); }
		public List<TerminalNode> Text() { return getTokens(CommandsGrammarParser.Text); }
		public TerminalNode Text(int i) {
			return getToken(CommandsGrammarParser.Text, i);
		}
		public TerminalNode Numbers() { return getToken(CommandsGrammarParser.Numbers, 0); }
		public NavigationCommandSimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigationCommandSimple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).enterNavigationCommandSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).exitNavigationCommandSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsGrammarVisitor ) return ((CommandsGrammarVisitor<? extends T>)visitor).visitNavigationCommandSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigationCommandSimpleContext navigationCommandSimple() throws RecognitionException {
		NavigationCommandSimpleContext _localctx = new NavigationCommandSimpleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_navigationCommandSimple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Text) {
				{
				{
				setState(57);
				match(Text);
				setState(58);
				match(T__0);
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(NavigationCommandKeyword);
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(65);
				match(T__0);
				setState(66);
				match(Numbers);
				}
				break;
			}
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(69);
				match(T__0);
				setState(70);
				match(Text);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public List<TerminalNode> Text() { return getTokens(CommandsGrammarParser.Text); }
		public TerminalNode Text(int i) {
			return getToken(CommandsGrammarParser.Text, i);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsGrammarListener ) ((CommandsGrammarListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsGrammarVisitor ) return ((CommandsGrammarVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(76);
				match(T__0);
				setState(77);
				match(Text);
				}
				}
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000bS\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\r\b\u0000\n\u0000\f\u0000\u0010\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0015\b\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u0019\b\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"\u001d\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0005\u0001%\b\u0001\n\u0001\f\u0001(\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0005\u0002/\b\u0002"+
		"\n\u0002\f\u00022\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003<\b\u0003"+
		"\n\u0003\f\u0003?\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"D\b\u0003\u0001\u0003\u0001\u0003\u0005\u0003H\b\u0003\n\u0003\f\u0003"+
		"K\t\u0003\u0001\u0004\u0001\u0004\u0004\u0004O\b\u0004\u000b\u0004\f\u0004"+
		"P\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000"+
		"W\u0000\u000e\u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u0004"+
		"0\u0001\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\bN\u0001\u0000"+
		"\u0000\u0000\n\u000b\u0005\n\u0000\u0000\u000b\r\u0005\u0001\u0000\u0000"+
		"\f\n\u0001\u0000\u0000\u0000\r\u0010\u0001\u0000\u0000\u0000\u000e\f\u0001"+
		"\u0000\u0000\u0000\u000e\u000f\u0001\u0000\u0000\u0000\u000f\u0011\u0001"+
		"\u0000\u0000\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0011\u0014\u0005"+
		"\u0005\u0000\u0000\u0012\u0013\u0005\u0001\u0000\u0000\u0013\u0015\u0005"+
		"\u0006\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001"+
		"\u0000\u0000\u0000\u0015\u0018\u0001\u0000\u0000\u0000\u0016\u0017\u0005"+
		"\u0001\u0000\u0000\u0017\u0019\u0005\u0007\u0000\u0000\u0018\u0016\u0001"+
		"\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u001c\u0001"+
		"\u0000\u0000\u0000\u001a\u001b\u0005\u0001\u0000\u0000\u001b\u001d\u0005"+
		"\b\u0000\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0001"+
		"\u0000\u0000\u001f \u0005\t\u0000\u0000 !\u0003\b\u0004\u0000!\u0001\u0001"+
		"\u0000\u0000\u0000\"#\u0005\n\u0000\u0000#%\u0005\u0001\u0000\u0000$\""+
		"\u0001\u0000\u0000\u0000%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000"+
		"\u0000&\'\u0001\u0000\u0000\u0000\')\u0001\u0000\u0000\u0000(&\u0001\u0000"+
		"\u0000\u0000)*\u0005\u0003\u0000\u0000*+\u0003\b\u0004\u0000+\u0003\u0001"+
		"\u0000\u0000\u0000,-\u0005\n\u0000\u0000-/\u0005\u0001\u0000\u0000.,\u0001"+
		"\u0000\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000013\u0001\u0000\u0000\u000020\u0001\u0000\u0000"+
		"\u000034\u0005\u0002\u0000\u000045\u0005\u0001\u0000\u000056\u0001\u0000"+
		"\u0000\u000067\u0005\t\u0000\u000078\u0003\b\u0004\u00008\u0005\u0001"+
		"\u0000\u0000\u00009:\u0005\n\u0000\u0000:<\u0005\u0001\u0000\u0000;9\u0001"+
		"\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000"+
		"=>\u0001\u0000\u0000\u0000>@\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000"+
		"\u0000@C\u0005\u0004\u0000\u0000AB\u0005\u0001\u0000\u0000BD\u0005\u000b"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DI\u0001"+
		"\u0000\u0000\u0000EF\u0005\u0001\u0000\u0000FH\u0005\n\u0000\u0000GE\u0001"+
		"\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000"+
		"IJ\u0001\u0000\u0000\u0000J\u0007\u0001\u0000\u0000\u0000KI\u0001\u0000"+
		"\u0000\u0000LM\u0005\u0001\u0000\u0000MO\u0005\n\u0000\u0000NL\u0001\u0000"+
		"\u0000\u0000OP\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000Q\t\u0001\u0000\u0000\u0000\n\u000e\u0014\u0018\u001c"+
		"&0=CIP";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}