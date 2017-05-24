# IJCAI_2017_SD
Contains code and benchmarks from the paper
Learning-Based Abstractions for Nonlinear Constraint Solving, IJCAI 17

Dependencies:
------------
*Python packages:
--oct2py
--cvxopt
--cvxpy
--numpy

*Proteus

*dReal

*z3

*timeout3 -- Can be found @ https://github.com/jbenet/bash-timeout3

Benchmarks and Corresponding Code:
-----------------------------------
-[Dubins Car Examples] The folder ./dubins_car has the problem instances described in the paper 
corresponding to MPC for system with discretized Dubins Car dynamics.

-[Hong Family (first quadrant)] The folder ./hong_family has the problem instances described in the 
paper corresponding to solving the Hong Family constraints in the first quadrant.

-[FPGA encoder expression] The folder ./FPGA_Encoder_Expression has the problem instances 
corresponding to the search for valid FPGA Encoder expressions.

The benchmark files are in the 'dr' format -- var: followed by the problem variables 
and bounds on the solution space, ctr: followed by the constraints.

Setup & Usage:
------
-Clone the repository to a local directory once you have all the dependencies.

-Import proteus into an IDE (eg. eclipse) and edit dRealInterface.java 
from dl.logicsolvers.drealkit as follows:

Replace 
"/home/sumanth/timeout3-v2" 
with "<path>/timeout3-v2" 
where <path> is the path to the binary 
corresponding to timeout3-v2.

-Running the FPGA encoder expression benchmarks

$cd ./naum_philips

$chmod +x configure_tmp.sh

$chmod +x configure.sh

$./configure.sh

$./configure_tmp.sh

$cd bin

$java Naum <filename> 0 1 1 (or 0) 0 0 


-naum takes as input the filename followed by a sequence of 5 0/1s. Eg. 0 1 1 0 0.
The leftmost bit corresponds to checking for unsat. The second leftmost bit corresponds 
to checking for sat and the middle bit corresponds to whether the constraints must be solved
direction or the abstraction based approach must be employed. The last 2 digits are not functional since 
much of the functionality in 'naum' has been removed for the code in this repository. 

Since all the 'hong' examples are unsat, usage is restricted 
to 1 0 1/0 0 0. For the Dubins Car Benchmarks, this is restricted to 0 1 1/0 0 0.

-For the other benchmarks, adjust the parameters as indicated in the paper.
