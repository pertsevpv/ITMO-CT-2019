composite(1).
prime(N):-
	\+ composite(N).

sorted(X, [H | T]):-
	X =< H.
sorted(X, []).

del_dubl([],[]):-!.

del_dubl([H | T], V):-
	member(H, T),!,
	del_dubl(T,V).

del_dubl([H | T], [H | V]):-
	\+ member(H, T),
	!,
	del_dubl(T,V).

next_div(N, X, D) :-
	X =< N,
	0 is mod(N, X),
	D is X.

next_div(N, X, D) :-
	X =< N,
	\+ 0 is mod(N, X),
	X1 is X + 1,
	next_div(N, X1, D).

prime_divisors(1, []).

prime_divisors(N, [H | T]):-
	number(N),
	!,
	next_div(N, 2, H),
	N1 is div(N, H),
	prime_divisors(N1, T).
	
prime_divisors(N, [H | T]):-
	sorted(H, T),
	prime_divisors(N1, T),
	N is H * N1.

unique_prime_divisors(N, D1):-
	prime_divisors(N, D),!,
	del_dubl(D,D1).

sieve(N, AN, MN):-
	N < MN,
	N1 is N + AN,
	assert(composite(N1)),
	sieve(N1, AN, MN).

init_sieve(N, MN):-
	prime(N),
	sieve(N, N, MN).

sieve_rec(CN, MN):-
	CN < MN,
	CN1 = CN + 1,
	\+ init_sieve(CN, MN),
	sieve_rec(CN1, MN).

init(N):-
	sieve_rec(2, N).