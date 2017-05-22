
%   Blocks World

%  To run this example, first consult the planner you want to use
%  (strips.pl or idstrips.pl) and then consult the blocks.pl example
%  In the query window, run the goal:
%  ?- plan.


% actions
act( pick_from_table(X),                             % action name
     [block(X), handempty,  clear(X), on(X,table)],  % preconditions
     [handempty, on(X, table)],                      % delete
     [holding(X)]                                    % add
     ).


act( pickup_from_block(X,Y),
     [block(X), handempty, clear(X), on(X,Y), block(Y), diff(X,Y)],  % (1)
     [handempty, on(X, Y)],
     [holding(X), clear(Y)]
     ).
% (1)  diff(X,Y) is needed to prevent cases like pickup_from_block(a,a)


act( putdown_on_table(X),
     [block(X), holding(X)],
     [holding(X)],
     [handempty, on(X,table)]
     ).


act(  putdown_on_block(X,Y),
     [block(X), holding(X), block(Y), clear(Y), diff(X,Y)],
     [holding(X), clear(Y)],
     [handempty, on(X,Y)]
     ).


goal_state( [on(c,b),on(a,c) ]).

initial_state(
     [      clear(b),
            clear(c),
            on(c,a),
            on(a,table),
            on(b,table),
            handempty,
            block(a),
            block(b),
            block(c),
            diff(a,b),
            diff(a,c),
            diff(b,a),
            diff(b,c),
            diff(c,a),
            diff(c,b),
            diff(a,table),
            diff(b,table),
            diff(c,table)
     ]).
     