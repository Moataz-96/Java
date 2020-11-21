	M1				STATS			START			0
2							EXTREF			XREAD,XWRITE
3							STL			RETADR
4							J			EXADDR
5				RETADR			RESW			1
6				SUM			RESW			1
7				SUMSQ			RESW			1
8				I			RESW			1
9				VALUE			RESW			1
10				MEAN			RESW			1
11				VARIANCE		RESW			1
12				{EXADDR}		LDA			#0
13							STA			SUM
14							LDA			#0
15							STA			SUMSQ
16							LDA			#1
17				{L1}			STA			I
18							COMP			#100
19							JGT			{L2}
20							+JSUB			XREAD
21							WORD			1
22							WORD			VALUE
23							LDA			SUM
24							ADD			VALUE
25							SUB			I
26							ADD			MEAN
27							SUB			SUMSQ
28							SUB			VARIANCE
29							STA			SUM
30							LDA			VALUE
31							MUL			VALUE
32							STA			T1
33							LDA			SUMSQ
34							ADD			T1
35							STA			SUMSQ
36							LDA			I
37							ADD			#1
38							J			L1
39				{L2}			LDA			SUM
40							DIV			#100
41							STA			T2
42							LDA			T2
43							STA			MEAN
44							LDA			SUMSQ
45							MUL			#100
46							STA			T3
47							LDA			T3
48							MUL			I
49							STA			T4
50							LDA			T4
51							MUL			#100
52							STA			T5
53							LDA			T5
54							MUL			I
55							STA			T6
56							LDA			#100
57							MUL			I
58							STA			T7
59							LDA			MEAN
60							MUL			VALUE
61							STA			T8
62							LDA			T8
63							DIV			SUM
64							STA			T9
65							LDA			T9
66							MUL			SUMSQ
67							STA			T10
68							LDA			T10
69							MUL			MEAN
70							STA			T11
71							LDA			T11
72							MUL			VALUE
73							STA			T12
74							LDA			T12
75							DIV			SUM
76							STA			T13
77							LDA			T13
78							MUL			SUMS
79							STA			T14
80							LDA			VARIANCE
81							MUL			SUM
82							STA			T15
83							LDA			MEAN
84							MUL			MEAN
85							STA			T16
86							LDA			T6
87							ADD			T7
88							ADD			SUM
89							ADD			VALUE
90							SUB			I
91							ADD			T14
92							SUB			SUMSQ
93							ADD			T15
94							SUB			T16
95							SUB			SUM
96							STA			VARIANCE
97							+JSUB			XWRITE
98							WORD			2
99							WORD			VARIANCE
100							WORD			MEAN
101							LDL			RETADR
102							RSUB
103				T1			RESW			1
104				T2			RESW			1
105				T3			RESW			1
106				T4			RESW			1
107				T5			RESW			1
108				T6			RESW			1
109				T7			RESW			1
110				T8			RESW			1
111				T9			RESW			1
112				T10			RESW			1
113				T11			RESW			1
114				T12			RESW			1
115				T13			RESW			1
116				T14			RESW			1
117				T15			RESW			1
118				T16			RESW			1
119							END
0				T13			RESW			1
121				T14			RESW			1
122				T15			RESW			1
123				T16			RESW			1
124				T17			RESW			1
125							END
