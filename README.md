# Local runner for CodinGame's Ghost in the Cell

Enables you to battle two AIs on your local machine.

Why?
* measuring progress of your AI against some benchmark
* (automatically) optimizing parameters by battling two slightly different versions against each other
* running your own tournament

How to use it?
RefereeRunner is the main class. It takes two parameters, both of them are complete commands to run an AI. If it requires multiple words enclose it in quotes

Example: "/home/null/bin/swift-3.0.2-RELEASE-ubuntu16.04/usr/bin/swift run /home/null/temp/Source/main.swift"

The output is the players scores. One per line.

State of things

Working prototype. Basic but useful. One of the design goals was keeping the original Referee.java untouched.
For some unknown reason it wasn't able to read output of my AI (writen in swift), so I changed the AI over to output to standard error instead of standard output. If the third argument to this runner is --use-stderr it will read AIs output from stderr.
