package fr.aubonmarche;

import java.time.LocalDate;

public interface Consumable {
	public boolean isRipe();
	public boolean isExpired(LocalDate dateVerification);
	public long daysRemainingBeforeExpiration(LocalDate dateVerification);
}
