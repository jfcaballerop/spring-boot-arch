import { HttpClient } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { take, tap } from 'rxjs/operators';


const apiUrl = 'http://localhost:8001' + '/educado';

@Injectable({
	providedIn: 'root'
})
export class ReactiveServiceService {
	private customersList: any[] = new Array();
	private dataStream: BehaviorSubject<Array<any>> = new BehaviorSubject<any[]>([]);
	private eventSource: EventSource;

	dataS = this.dataStream.asObservable();

	constructor(private http: HttpClient, private zone: NgZone) {
		this.eventSource = new EventSource("events");

	}

	public startStream(): void {

		this.eventSource = new EventSource(apiUrl);
		this.eventSource.onmessage = (event) => {

			console.log('got event data', event['data']);
			const newArrays = [...this.dataStream.value, JSON.parse(event['data'])];
			this.zone.run(() => {
				this.dataStream.next(newArrays);
			});
			this.eventSource.onerror = (error) => {
				this.zone.run(() => {
					if (this.eventSource.readyState === 0) {
						this.eventSource.close();
						this.dataStream.complete();
					} else {
						this.dataStream.error('EventSource error: ' + error);
					}
				});
			}
		}
	}

	public onClose() {
		this.eventSource.close();
		this.dataStream.complete();

	}
	getReactive(): Observable<any[]> {
		this.customersList = new Array();


		return this.http.get<any[]>(apiUrl)
			.pipe(
				take(1),
				tap(data => {
					this.customersList = data;
					console.log(data);
				}
				)
			)
	}

}
