package domain;

import java.util.ArrayList;

public class ExtendedIteratorEvents implements extendedIterator<Event> {

	ArrayList<Event> list = new ArrayList<Event>();
	int pos = 0;

	public ExtendedIteratorEvents(ArrayList<Event> e) {
		this.list = e;
	}

	@Override
	public boolean hasNext() {
		if (pos < list.size()) {
			return (list.get(pos) != null);
		} else
			return false;
	}

	@Override
	public Event next() {
		pos += 1;
		return list.get(pos - 1);
	}

	@Override
	public Event previous() {
		pos -= 1;
		return list.get(pos + 1);
	}

	@Override
	public boolean hasPrevious() {
		if (pos >= 0) {
			return (list.get(pos) != null);
		} else
			return false;
	}

	@Override
	public void goFirst() {
		pos = 0;
	}

	@Override
	public void goLast() {
		pos = list.size() - 1;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Event e) {
		return this.list.contains(e);
	}

}
