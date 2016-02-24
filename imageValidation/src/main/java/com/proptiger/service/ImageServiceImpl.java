package com.proptiger.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.proptiger.model.Images;
import com.proptiger.model.Tags;
import com.proptiger.repo.ImagesDao;
import com.proptiger.repo.TagsDao;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImagesDao imagesDao;

	@Autowired
	TagsDao tagsDao;

	private static final String APP_ID = "api.id";//"Te9XxJTbMqebovrmHvWFWplwhOVeDgIUb2jrs9J3";
	private static final String APP_SECRET = "api.secret";//"ciHTkvIfaBCFcEKoaY3Kuja0WLzB0ZSJ9oh1mVXr";

	public List<Images> getFromExisting() {
		List<Images> list = imagesDao.findAll();
		return list;
	}

	public void passOneByOne(Images item) {
		ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(item.getUrl()));
		System.out.println(item.getUrl());
		Tags tags = null;
		for (Tag tag : results.get(0).getTags()) {
			tags = new Tags();

			tags.setId(item.getId());
			tags.setUrl(item.getUrl());
			tags.setTag(tag.getName());
			tags.setProbability(tag.getProbability());
			tagsDao.save(tags);
			System.out.println(tag.getName() + ": " + tag.getProbability());
		}
	}

	public void fetchFromApi() {
		List<Images> list = null;
		list = getFromExisting();

		for (ListIterator<Images> iter = list.listIterator(); iter.hasNext();) {
			Images img = iter.next();
			passOneByOne(img);

		}
	}

}
