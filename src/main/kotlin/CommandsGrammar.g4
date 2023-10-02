grammar CommandsGrammar;
classCommand:
(Text ' ')* ClassCommandKeyword (' ' PrivacyKeyword)? (' ' StaticKeyword)? (' ' FinalKeyword)? ' ' TypeKeyword name;

classCommandSetterAndGetter:
(Text ' ')* TypeToCreate name;

addParameterInMethod:
(Text ' ')* (Parameter' ') TypeKeyword name;

navigationCommandSimple:
(Text ' ')* NavigationCommandKeyword (' ' Numbers)? (' ' Text)*;

name:(' ' Text)+;

Parameter: 'parameter' | 'argument';
TypeToCreate:'setter' | 'getter' | 'gather' | 'constructor';
NavigationCommandKeyword: 'up' | 'down' | 'left' | 'right'| 'enter';
ClassCommandKeyword: 'method' | 'field declaration';
PrivacyKeyword: 'private' |'public';
StaticKeyword: 'static';
FinalKeyword: 'final';
TypeKeyword: 'int' |'float' |'double' |'boolean' | 'long' | 'short' | 'char' | 'string';
Text: [a-zA-Z]+;
Numbers:[0-9]+;
