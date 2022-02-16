import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ReactiveServiceService } from './reactive-service.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
	title = 'test-reactive';
	items: any[] = []
	dataInfo$: Observable<any[]> = new Observable<any[]>();

	constructor(private reactService: ReactiveServiceService) { }

	ngOnInit(): void {
		this.reactService.startStream();
		this.dataInfo$ = this.reactService.dataS;


	}

	ngOnDestroy() {
		this.reactService.onClose();
	}

	@HostListener('window:beforeunload', ['$event'])
	unloadHandler(event: any) {
		console.log('unloadHandler');
		this.reactService.onClose();
	}
}
