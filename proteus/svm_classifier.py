from oct2py import octave
from cvxopt import matrix, solvers
from cvxpy import *
import numpy as np
def svm_classifier(X, y,mode):

	[num_sample,d]=X.shape
	mode_control=np.ones((num_sample,1))
	for i  in range(num_sample):
	    if(mode==1):
			if(y[i]==-1):
		    		mode_control[i]=0;
	    if(mode==2):
			if(y[i]==1):
		    		mode_control[i]=0;
	    if(mode==4):
	    			mode_control[i]=0;
	#[G,beta]= octave.PWL_feature(X, M, beta_type);

	A=np.array([[]])
	for i in range(num_sample):
		s=np.zeros((1,num_sample));
		s[0,i]=1;
		temp_1=y[i]*np.concatenate((np.array([[1]]),[X[i,:]]),axis=1)
		temp_a=-np.concatenate((temp_1,s),axis=1)
		if(i==0):
			A=temp_a
		else:
			A=np.concatenate((A,temp_a),axis=0)
	dum_concat=-np.concatenate((np.zeros((num_sample,d+1)),np.eye(num_sample)),axis=1)
	A=np.concatenate((A,dum_concat),axis=0);
	beq=np.zeros((1+d+num_sample,1));
	Aeq=np.concatenate((np.zeros((d+1,1)),np.ones((num_sample,1))-mode_control),axis=0);
	Aeq=np.diag(Aeq[:,0]);


	b=np.concatenate((-np.ones((num_sample,1)), np.zeros((num_sample,1))),axis=0);
	gamma=1;
    	x=Variable(d+num_sample+1,1)
	constraints=[A*x<=b, Aeq*x==0]
	obj=Minimize(( 0.5*(norm(x[0:d+1:1,0])**2) )+100*(sum_entries(x[d+1:d+1+num_sample:1,0])) )
	prob = Problem(obj, constraints)
	prob.solve()
	x_val=x.value
	ypredicted = x_val[0,0]+(X*x_val[1:1+d,0]);
	ypredicted=np.sign(ypredicted);
	error=np.zeros(y.shape[0]);
	for i in range(y.shape[0]):
		error[i]=(y[i]-ypredicted[i,0])/2
	return (error,x.value[0:d+1],ypredicted)
