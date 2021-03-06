package de.zabuza.sparkle.freewar.movement;

import java.awt.Point;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import de.zabuza.pathweaver.network.DirectedWeightedEdge;
import de.zabuza.pathweaver.network.Path;
import de.zabuza.sparkle.freewar.inventory.IInventory;
import de.zabuza.sparkle.freewar.location.ILocation;
import de.zabuza.sparkle.freewar.movement.network.EMoveType;
import de.zabuza.sparkle.freewar.movement.network.FreewarNode;
import de.zabuza.sparkle.freewar.movement.network.NetworkUtil;

/**
 * Thread which runs a movement task. This moves the given player to a given
 * position.
 * 
 * @author Zabuza {@literal <zabuza.dev@gmail.com>}
 *
 */
public final class MovementTask extends Thread {
	/**
	 * The instance of a task which is cancelled and has already terminated.
	 */
	private static MovementTask canceledTaskInstance;

	/**
	 * Timeout in milliseconds to wait for the move waiting cycle to check its
	 * condition.
	 */
	private static final long MOVE_WAITING_TIMEOUT = 500;

	/**
	 * Creates and returns an instance of a task which is cancelled and has already
	 * terminated.
	 * 
	 * @return An instance of a task which is cancelled and has already terminated
	 */
	public static MovementTask createCanceledTask() {
		if (canceledTaskInstance == null) {
			canceledTaskInstance = new MovementTask(null, null, null, null);
			canceledTaskInstance.cancelTask();
			canceledTaskInstance.start();
		}
		return canceledTaskInstance;
	}

	/**
	 * Whether the task terminated.
	 */
	private boolean mHasTerminated;
	/**
	 * The object to use for accessing the inventory of the current instance.
	 */
	private final IInventory mInventory;
	/**
	 * The location object of the current instance.
	 */
	private final ILocation mLocation;
	/**
	 * The movement object of the current instance
	 */
	private final IMovement mMovement;
	/**
	 * The path to move along.
	 */
	private final Path mPath;
	/**
	 * Whether the task was canceled, if not and the task has terminated then it was
	 * successful.
	 */
	private boolean mWasCanceled;

	/**
	 * Tries to move the player along the given path when started.
	 * 
	 * @param path
	 *            The path to move the player along
	 * @param location
	 *            The location object of the current instance
	 * @param movement
	 *            The movement object of the current instance
	 * @param inventory
	 *            The object to use for accessing the inventory of the current
	 *            instance
	 */
	public MovementTask(final Path path, final ILocation location, final IMovement movement,
			final IInventory inventory) {
		this.mPath = path;
		this.mWasCanceled = false;
		this.mHasTerminated = false;
		this.mLocation = location;
		this.mMovement = movement;
		this.mInventory = inventory;
	}

	/**
	 * Cancels this task.
	 */
	public void cancelTask() {
		this.mWasCanceled = true;
	}

	/**
	 * Returns whether the task has terminated.
	 * 
	 * @return <tt>True</tt> if the task has terminated, <tt>false</tt> otherwise
	 */
	public boolean hasTerminated() {
		return this.mHasTerminated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		if (this.mWasCanceled) {
			finalizeTask();
		}

		final Iterator<DirectedWeightedEdge> edgeIter = this.mPath.getEdges().iterator();
		Point lastPos = null;
		while (!this.mWasCanceled && edgeIter.hasNext()) {
			final DirectedWeightedEdge edge = edgeIter.next();

			// Wait for the player to be able to move
			while (!this.mMovement.canMove() || this.mLocation.getPosition().equals(lastPos)) {
				try {
					TimeUnit.MILLISECONDS.sleep(MOVE_WAITING_TIMEOUT);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Check if the player still is at the assumed position
			final FreewarNode source = (FreewarNode) edge.getSource();
			final Point currentPos = this.mLocation.getPosition();
			lastPos = currentPos;
			if (source.getXCoordinate() != (int) currentPos.getX()
					|| source.getYCoordinate() != (int) currentPos.getY()) {
				cancelTask();
			}

			final Point sourcePos = currentPos;
			final FreewarNode destination = (FreewarNode) edge.getDestination();
			final Point destinationPos = new Point(destination.getXCoordinate(), destination.getYCoordinate());
			final EMoveType type = NetworkUtil.getMoveTypeOfCost(edge.getCost());

			// Execute the movement represented by the edge
			final boolean wasSuccessful = NetworkUtil.executeMovement(type, sourcePos, destinationPos, this.mMovement,
					this.mInventory);
			if (!wasSuccessful) {
				cancelTask();
			}
		}

		finalizeTask();
	}

	/**
	 * Returns whether the task was canceled. If it was not canceled but terminated,
	 * then the task was successful.
	 * 
	 * @return <tt>True</tt> if the task was canceled, <tt>false</tt> otherwise
	 */
	public boolean wasCanceled() {
		return this.mWasCanceled;
	}

	/**
	 * Finalizes and ends the task successful.
	 */
	private void finalizeTask() {
		this.mHasTerminated = true;
	}
}
