// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
package src;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TestLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Parameter=2, TypeToCreate=3, NavigationCommandKeyword=4, ClassCommandKeyword=5, 
		PrivacyKeyword=6, StaticKeyword=7, FinalKeyword=8, TypeKeyword=9, Text=10, 
		Numbers=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "Parameter", "TypeToCreate", "NavigationCommandKeyword", "ClassCommandKeyword", 
			"PrivacyKeyword", "StaticKeyword", "FinalKeyword", "TypeKeyword", "Text", 
			"Numbers"
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


	public TestLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CommandsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000b\u00c4\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001+\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002J\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003`\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004y\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0088\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u00b9\b\b\u0001\t\u0004\t\u00bc\b\t\u000b\t\f\t\u00bd\u0001\n\u0004"+
		"\n\u00c1\b\n\u000b\n\f\n\u00c2\u0000\u0000\u000b\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0001\u0000\u0002\u0002\u0000AZaz\u0001\u000009\u00d5\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0003*\u0001"+
		"\u0000\u0000\u0000\u0005I\u0001\u0000\u0000\u0000\u0007_\u0001\u0000\u0000"+
		"\u0000\tx\u0001\u0000\u0000\u0000\u000b\u0087\u0001\u0000\u0000\u0000"+
		"\r\u0089\u0001\u0000\u0000\u0000\u000f\u0090\u0001\u0000\u0000\u0000\u0011"+
		"\u00b8\u0001\u0000\u0000\u0000\u0013\u00bb\u0001\u0000\u0000\u0000\u0015"+
		"\u00c0\u0001\u0000\u0000\u0000\u0017\u0018\u0005 \u0000\u0000\u0018\u0002"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0005p\u0000\u0000\u001a\u001b\u0005"+
		"a\u0000\u0000\u001b\u001c\u0005r\u0000\u0000\u001c\u001d\u0005a\u0000"+
		"\u0000\u001d\u001e\u0005m\u0000\u0000\u001e\u001f\u0005e\u0000\u0000\u001f"+
		" \u0005t\u0000\u0000 !\u0005e\u0000\u0000!+\u0005r\u0000\u0000\"#\u0005"+
		"a\u0000\u0000#$\u0005r\u0000\u0000$%\u0005g\u0000\u0000%&\u0005u\u0000"+
		"\u0000&\'\u0005m\u0000\u0000\'(\u0005e\u0000\u0000()\u0005n\u0000\u0000"+
		")+\u0005t\u0000\u0000*\u0019\u0001\u0000\u0000\u0000*\"\u0001\u0000\u0000"+
		"\u0000+\u0004\u0001\u0000\u0000\u0000,-\u0005s\u0000\u0000-.\u0005e\u0000"+
		"\u0000./\u0005t\u0000\u0000/0\u0005t\u0000\u000001\u0005e\u0000\u0000"+
		"1J\u0005r\u0000\u000023\u0005g\u0000\u000034\u0005e\u0000\u000045\u0005"+
		"t\u0000\u000056\u0005t\u0000\u000067\u0005e\u0000\u00007J\u0005r\u0000"+
		"\u000089\u0005g\u0000\u00009:\u0005a\u0000\u0000:;\u0005t\u0000\u0000"+
		";<\u0005h\u0000\u0000<=\u0005e\u0000\u0000=J\u0005r\u0000\u0000>?\u0005"+
		"c\u0000\u0000?@\u0005o\u0000\u0000@A\u0005n\u0000\u0000AB\u0005s\u0000"+
		"\u0000BC\u0005t\u0000\u0000CD\u0005r\u0000\u0000DE\u0005u\u0000\u0000"+
		"EF\u0005c\u0000\u0000FG\u0005t\u0000\u0000GH\u0005o\u0000\u0000HJ\u0005"+
		"r\u0000\u0000I,\u0001\u0000\u0000\u0000I2\u0001\u0000\u0000\u0000I8\u0001"+
		"\u0000\u0000\u0000I>\u0001\u0000\u0000\u0000J\u0006\u0001\u0000\u0000"+
		"\u0000KL\u0005u\u0000\u0000L`\u0005p\u0000\u0000MN\u0005d\u0000\u0000"+
		"NO\u0005o\u0000\u0000OP\u0005w\u0000\u0000P`\u0005n\u0000\u0000QR\u0005"+
		"l\u0000\u0000RS\u0005e\u0000\u0000ST\u0005f\u0000\u0000T`\u0005t\u0000"+
		"\u0000UV\u0005r\u0000\u0000VW\u0005i\u0000\u0000WX\u0005g\u0000\u0000"+
		"XY\u0005h\u0000\u0000Y`\u0005t\u0000\u0000Z[\u0005e\u0000\u0000[\\\u0005"+
		"n\u0000\u0000\\]\u0005t\u0000\u0000]^\u0005e\u0000\u0000^`\u0005r\u0000"+
		"\u0000_K\u0001\u0000\u0000\u0000_M\u0001\u0000\u0000\u0000_Q\u0001\u0000"+
		"\u0000\u0000_U\u0001\u0000\u0000\u0000_Z\u0001\u0000\u0000\u0000`\b\u0001"+
		"\u0000\u0000\u0000ab\u0005m\u0000\u0000bc\u0005e\u0000\u0000cd\u0005t"+
		"\u0000\u0000de\u0005h\u0000\u0000ef\u0005o\u0000\u0000fy\u0005d\u0000"+
		"\u0000gh\u0005f\u0000\u0000hi\u0005i\u0000\u0000ij\u0005e\u0000\u0000"+
		"jk\u0005l\u0000\u0000kl\u0005d\u0000\u0000lm\u0005 \u0000\u0000mn\u0005"+
		"d\u0000\u0000no\u0005e\u0000\u0000op\u0005c\u0000\u0000pq\u0005l\u0000"+
		"\u0000qr\u0005a\u0000\u0000rs\u0005r\u0000\u0000st\u0005a\u0000\u0000"+
		"tu\u0005t\u0000\u0000uv\u0005i\u0000\u0000vw\u0005o\u0000\u0000wy\u0005"+
		"n\u0000\u0000xa\u0001\u0000\u0000\u0000xg\u0001\u0000\u0000\u0000y\n\u0001"+
		"\u0000\u0000\u0000z{\u0005p\u0000\u0000{|\u0005r\u0000\u0000|}\u0005i"+
		"\u0000\u0000}~\u0005v\u0000\u0000~\u007f\u0005a\u0000\u0000\u007f\u0080"+
		"\u0005t\u0000\u0000\u0080\u0088\u0005e\u0000\u0000\u0081\u0082\u0005p"+
		"\u0000\u0000\u0082\u0083\u0005u\u0000\u0000\u0083\u0084\u0005b\u0000\u0000"+
		"\u0084\u0085\u0005l\u0000\u0000\u0085\u0086\u0005i\u0000\u0000\u0086\u0088"+
		"\u0005c\u0000\u0000\u0087z\u0001\u0000\u0000\u0000\u0087\u0081\u0001\u0000"+
		"\u0000\u0000\u0088\f\u0001\u0000\u0000\u0000\u0089\u008a\u0005s\u0000"+
		"\u0000\u008a\u008b\u0005t\u0000\u0000\u008b\u008c\u0005a\u0000\u0000\u008c"+
		"\u008d\u0005t\u0000\u0000\u008d\u008e\u0005i\u0000\u0000\u008e\u008f\u0005"+
		"c\u0000\u0000\u008f\u000e\u0001\u0000\u0000\u0000\u0090\u0091\u0005f\u0000"+
		"\u0000\u0091\u0092\u0005i\u0000\u0000\u0092\u0093\u0005n\u0000\u0000\u0093"+
		"\u0094\u0005a\u0000\u0000\u0094\u0095\u0005l\u0000\u0000\u0095\u0010\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0005i\u0000\u0000\u0097\u0098\u0005n\u0000"+
		"\u0000\u0098\u00b9\u0005t\u0000\u0000\u0099\u009a\u0005f\u0000\u0000\u009a"+
		"\u009b\u0005l\u0000\u0000\u009b\u009c\u0005o\u0000\u0000\u009c\u009d\u0005"+
		"a\u0000\u0000\u009d\u00b9\u0005t\u0000\u0000\u009e\u009f\u0005d\u0000"+
		"\u0000\u009f\u00a0\u0005o\u0000\u0000\u00a0\u00a1\u0005u\u0000\u0000\u00a1"+
		"\u00a2\u0005b\u0000\u0000\u00a2\u00a3\u0005l\u0000\u0000\u00a3\u00b9\u0005"+
		"e\u0000\u0000\u00a4\u00a5\u0005b\u0000\u0000\u00a5\u00a6\u0005o\u0000"+
		"\u0000\u00a6\u00a7\u0005o\u0000\u0000\u00a7\u00a8\u0005l\u0000\u0000\u00a8"+
		"\u00a9\u0005e\u0000\u0000\u00a9\u00aa\u0005a\u0000\u0000\u00aa\u00b9\u0005"+
		"n\u0000\u0000\u00ab\u00ac\u0005l\u0000\u0000\u00ac\u00ad\u0005o\u0000"+
		"\u0000\u00ad\u00ae\u0005n\u0000\u0000\u00ae\u00b9\u0005g\u0000\u0000\u00af"+
		"\u00b0\u0005s\u0000\u0000\u00b0\u00b1\u0005h\u0000\u0000\u00b1\u00b2\u0005"+
		"o\u0000\u0000\u00b2\u00b3\u0005r\u0000\u0000\u00b3\u00b9\u0005t\u0000"+
		"\u0000\u00b4\u00b5\u0005c\u0000\u0000\u00b5\u00b6\u0005h\u0000\u0000\u00b6"+
		"\u00b7\u0005a\u0000\u0000\u00b7\u00b9\u0005r\u0000\u0000\u00b8\u0096\u0001"+
		"\u0000\u0000\u0000\u00b8\u0099\u0001\u0000\u0000\u0000\u00b8\u009e\u0001"+
		"\u0000\u0000\u0000\u00b8\u00a4\u0001\u0000\u0000\u0000\u00b8\u00ab\u0001"+
		"\u0000\u0000\u0000\u00b8\u00af\u0001\u0000\u0000\u0000\u00b8\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b9\u0012\u0001\u0000\u0000\u0000\u00ba\u00bc\u0007"+
		"\u0000\u0000\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001"+
		"\u0000\u0000\u0000\u00be\u0014\u0001\u0000\u0000\u0000\u00bf\u00c1\u0007"+
		"\u0001\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c3\u0016\u0001\u0000\u0000\u0000\t\u0000*I_x\u0087"+
		"\u00b8\u00bd\u00c2\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}