
% actions

% go from FromRoom to ToRoom
act( go(FromRoom, ToRoom),                                                      % action name
     [shakey(S), in(S, FromRoom), on(S, floor), connect(FromRoom, ToRoom) ],    % preconditions
     [in(S, FromRoom)],                                                         % delete
     [in(S, ToRoom)]                                                             % add
     ).

% push box Box from PosA to PosB
act( push(Box, PosA, PosB),
     [shakey(S), box(Box), on(S, floor), in(S, PosA), in(Box, PosA), connect(PosA, PosB) ],
     [in(Box, PosA), in(S, PosA)],
     [in(Box, PosB), in(S, PosB)]
     ).

% climb up/down from a box
act( climbUp(Box),
     [shakey(S), box(Box), on(S, floor), in(S, Pos),in(Box, Pos)],
     [on(S, floor) ],
     [on(S, Box)]
     ).

act( climbDown(Box),
     [shakey(S), box(Box), on(S, Box), in(S, Room), in(Box, Room)],
     [on(S, Box)],
     [on(S, floor)]
     ).

% turn on/off light switch
act( turnOn(),
     [shakey(S), box(Box), light(L, false), in(S, Pos), in(Box, Pos), on(S, Box)],
     [light(L, false)],
     [light(L, true)]
     ).

act( turnOff(),
     [shakey(S), box(Box), light(L, true), in(S, Pos), in(Box, Pos), on(S, Box)],
     [light(L, true)],
     [light(L, false)]
     ).



goal_state( 
    [
    light(light1, false)
    ]).

initial_state(
     [  
        %  Set up shakey   
        shakey(s),
        in(s, room3),
        on(s, floor),

        % Box
        box(box1),
        box(box2),
        box(box3),
        box(box4),

        in(box1, room1),
        in(box2, room1),
        in(box3, room1),
        in(box4, room1),

        % Rooms 
        room(room1),
        room(room2),
        room(room3),
        room(room4),
        room(corridor),

        % Connections
        connect(room1, corridor),
        connect(room2, corridor),
        connect(room3, corridor),  
        connect(room4, corridor),

        connect(corridor, room1),
        connect(corridor, room2),
        connect(corridor, room3),  
        connect(corridor, room4),

        connect(light1, room1),
        connect(light2, room2),
        connect(light3, room3),
        connect(light4, room4),

        connect(room1, light1),
        connect(room2, light2),
        connect(room3, light3),
        connect(room4, light4),

        % Light switches
        light(light1, true),
        light(light2, false),
        light(light3, false),
        light(light4, true)


     ]).
     